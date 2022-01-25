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

/**
 * CrawlingNode, script
 * @author macle
 */
public class CrawlingNodeScript {

    private final CrawlingNode crawlingNode;
    private final String script;
    private final JsonObject scriptObj;

    /**
     * 생성자
     * @param crawlingNode CrawlingNode
     * @param script String script
     */
    public CrawlingNodeScript(CrawlingNode crawlingNode, String script, JsonObject scriptObj){
        this.crawlingNode = crawlingNode;
        this.script = script;
        this.scriptObj = scriptObj;
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
        return script;
    }

    /**
     * @return scriptObject jsonObject
     */
    public JsonObject getScriptObj() {
        return scriptObj;
    }
}
