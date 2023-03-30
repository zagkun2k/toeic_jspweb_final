package com.truonggiang.web.command;

import com.truonggiang.core.dto.ListenGuidelineDTO;
import com.truonggiang.core.persistence.entity.ListenGuidelineEntity;
import com.truonggiang.core.web.command.AbstractCommand;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO> {
    public ListenGuidelineCommand() {
        this.pojo = new ListenGuidelineDTO();
    }
}
