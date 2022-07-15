/*
 * Copyright (C) 2020 Seomse Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seomse.crawling.node;

import com.google.gson.JsonObject;
import com.seomse.crawling.core.http.HttpMessage;

/**
 * CrawlingNode, script
 * @author macle
 */
public class CrawlingNodeMessage {

    private final CrawlingNode crawlingNode;
    private final HttpMessage httpMessage;


    public CrawlingNodeMessage(CrawlingNode crawlingNode, HttpMessage message){
        this.crawlingNode = crawlingNode;
        this.httpMessage = message;

    }

    /**
     * @return CrawlingNode
     */
    public CrawlingNode getCrawlingNode() {
        return crawlingNode;
    }

    /**
     * @return script String
     */
    public String getScript() {
        return httpMessage.getMessage();
    }

    public HttpMessage getMessage() {
        return httpMessage;
    }


}
