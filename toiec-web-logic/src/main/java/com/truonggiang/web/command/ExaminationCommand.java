package com.truonggiang.web.command;

import com.truonggiang.core.dto.ExaminationDTO;
import com.truonggiang.core.dto.UserDTO;
import com.truonggiang.core.web.command.AbstractCommand;

public class ExaminationCommand extends AbstractCommand<ExaminationDTO> {
    public ExaminationCommand() {
        this.pojo = new ExaminationDTO();
    }
}
