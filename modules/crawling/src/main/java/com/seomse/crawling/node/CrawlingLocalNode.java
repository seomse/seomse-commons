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

import com.seomse.crawling.core.http.HttpMessage;
import com.seomse.crawling.core.http.HttpUrl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
/**
 * local node
 * @author macle
 */
@Slf4j
public class CrawlingLocalNode extends CrawlingNode {

	@Override
	public String getHttpScript(String url, JSONObject optionData){
		log.debug("local node seq: " + seq);
		return HttpUrl.getScript(url, optionData);

	}

	@Override
	public HttpMessage getHttpMessage(String url, JSONObject optionData) {
		log.debug("local node seq: " + seq);
		return HttpUrl.getMessage(url, optionData);
	}
}
