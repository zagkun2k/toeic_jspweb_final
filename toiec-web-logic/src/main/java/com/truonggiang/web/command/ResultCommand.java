package com.truonggiang.web.command;

import com.truonggiang.core.dto.ResultDTO;
import com.truonggiang.core.dto.UserDTO;
import com.truonggiang.core.web.command.AbstractCommand;

public class ResultCommand extends AbstractCommand<ResultDTO> {
    public ResultCommand() {
        this.pojo = new ResultDTO();
    }
}
