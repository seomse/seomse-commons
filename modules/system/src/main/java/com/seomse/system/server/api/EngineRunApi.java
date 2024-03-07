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
package com.seomse.system.server.api;

import com.seomse.api.ApiMessage;
import com.seomse.api.Messages;
import com.seomse.commons.utils.ExceptionUtil;
import com.seomse.system.server.Server;
import com.seomse.system.server.run.EngineRun;
import com.seomse.system.server.run.EngineRunFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * engine run api
 * @author macle
 */
@Slf4j
public class EngineRunApi extends ApiMessage {

	@Override
	public void receive(String message) {

		try{
			Server server = Server.getInstance();
			EngineRun engineRun = EngineRunFactory.newEngineRun(server.getOsType());
			sendMessage(engineRun.start(message));
		}catch(Exception e){
			log.error(ExceptionUtil.getStackTrace(e));
			sendMessage(Messages.FAIL + ExceptionUtil.getStackTrace(e));
		}

	}

}
