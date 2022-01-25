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

package com.seomse.commons.example;

import com.seomse.commons.data.BeginEnd;
import com.seomse.commons.utils.string.highlight.StringHighlight;

/**
 * @author macle
 */
public class StringHighlightExample {

    public static void main(String[] args) {

        String text = "섬세한사람들 작더라도 쓸모있게 만드는 개발자 모임이다.";

        BeginEnd se1 = new BeginEnd() {
            @Override
            public int getBegin() {
                return 0;
            }

            @Override
            public int getEnd() {
                return 6;
            }
        };

        BeginEnd se2 = new BeginEnd() {
            @Override
            public int getBegin() {
                return 21;
            }

            @Override
            public int getEnd() {
                return 24;
            }
        };

        BeginEnd se3 = new BeginEnd() {
            @Override
            public int getBegin() {
                return 25;
            }

            @Override
            public int getEnd() {
                return 27;
            }
        };

        BeginEnd[] beginEnds = new BeginEnd[3];
        beginEnds[0] = se1;
        beginEnds[1] = se2;
        beginEnds[2] = se3;

        System.out.println(StringHighlight.make(text,"<em>","</em>", beginEnds));
    }

}
