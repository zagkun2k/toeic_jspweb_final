package com.truonggiang.web.command;

import com.truonggiang.core.dto.ExerciseDTO;
import com.truonggiang.core.dto.UserDTO;
import com.truonggiang.core.web.command.AbstractCommand;

public class ExerciseCommand extends AbstractCommand<ExerciseDTO> {
    public ExerciseCommand() {
        this.pojo = new ExerciseDTO();
    }
}
