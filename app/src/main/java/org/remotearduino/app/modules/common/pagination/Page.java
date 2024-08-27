package org.remotearduino.app.modules.common.pagination;

import java.util.List;

public record Page<T>(
        int page,
        int size,
        List<? extends T> data
) {}
