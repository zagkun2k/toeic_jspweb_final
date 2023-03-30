package com.truonggiang.core.common.utils;

import com.truonggiang.core.common.constant.CoreConstant;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//java.io.tmpdir
public class UploadUtil {
    private final int maxMemorySize = 1024 * 1024 * 3;
    private final int maxRequestSize = 1024 * 1024 * 50;
    private final Logger logger = Logger.getLogger(this.getClass());

    public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValue, String path) {

        ServletContext context = request.getServletContext();
//        String address = context.getRealPath("image");
//        String address = "D:\\Course\\TrungTamJava_SpringMVC\\demoMVC\\toiec-web\\src\\main\\webapp\\fileupload";
        String address = "/" + CoreConstant.UPLOAD_FILE;
        checkNotFolderToCreate(address, path);
        Boolean check = true;
        String fileLocation = null;
        String name = null;
        Map<String, String> mapReturnValue = new HashMap<>();


        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
//            System.out.println("Have not enctype multipart/data");// Check
            check = false;
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(maxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(maxRequestSize);
        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (!item.isFormField()) {
                    name = item.getName();
                    if (StringUtils.isNotBlank(name)) {
                        fileLocation = address + File.separator + path + File.separator + name;
                        File uploadFile = new File(fileLocation);

                        boolean isFileExist = uploadFile.exists();
                        try {
                            item.write(uploadFile);
                            if (isFileExist) {
                                uploadFile.delete();
                                item.write(uploadFile);
                            }
                        } catch (Exception e) {
                            check = false;
                            logger.error(e.getMessage(), e);
                        }
                    }
                } else {
                    if (titleValue != null) {
                        String nameField = item.getFieldName();
                        String valueField = null;
                        try {
                            valueField = item.getString("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                        if (titleValue.contains(nameField)) {
                            mapReturnValue.put(nameField, valueField);
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            check = false;
            logger.error(e.getMessage(), e);
        }

        String fileName = "";
        if (StringUtils.isNotBlank(name)) {
            fileName = path + File.separator + name;
        }

        return new Object[]{check, fileLocation, fileName, mapReturnValue};
    }

    private void checkNotFolderToCreate(String address, String path) {
        File rootFolder = new File(address);
        if (!rootFolder.exists()) {
            rootFolder.mkdirs();
        }

        File childFolder = new File(address + File.separator + path);
        if (!childFolder.exists()) {
            childFolder.mkdirs();
        }
    }
}