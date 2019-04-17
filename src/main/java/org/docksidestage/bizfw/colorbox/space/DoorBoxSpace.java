/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.colorbox.space;

import org.docksidestage.bizfw.colorbox.size.BoxSize;

/**
 * @author jflute
 */
public class DoorBoxSpace extends BoxSpace {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private boolean open;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public DoorBoxSpace(BoxSize size) {
        super(size);
    }

    @Override
    public Object getContent() {
        if (!isOpen()) {
            throw new IllegalStateException("Failed to get content because of closed.");
        }
        return super.getContent();
    }

    @Override
    public void setContent(Object content) {
        if (!isOpen()) {
            throw new IllegalStateException("Failed to get content because of closed.");
        }
        super.setContent(content);
    }

    // ===================================================================================
    //                                                                          Open/Close
    //                                                                          ==========
    public boolean isOpen() {
        return open;
    }

    public void openTheDoor() {
        open = true;
    }
}
