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

import com.seomse.commons.data.BeginEndImpl;
import com.seomse.commons.utils.string.highlight.StringHighlight;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * keyword 검색을 활용한 Highlight 예제
 * @author macle
 */
public class StringHighlightKeywordExample {
    public static void main(String[] args) {

        String json = "{\"tokens\":\"AI기반 AI 기반 팩트 미생물 미생 환경복원 환경 복원 미래 유망 기술 서울 경제 클릭 전달 특성 가짜 뉴스 인한 피해 확산 실시간 사실 여부 판별 인공 지능 소프트 웨어 기술 등장 전망 한국과학기술기획평가원 기반 팩트 기술 올해 미래 유망 기술 선정 기술 추정 미디어 관련 인공 지능 시장 자연어 처리 시장 핵심 예상 사물 인터넷 기반 상황 인식 조광 기술 능동 제어 소음 저감 기술 원전 사고 대응 시스템 비방 사성 비파 검사 기술 초미 먼지 제거 기술 친환경 녹조 제거 기술 생활 폐기물 첨단 분류 재활용 시스템 환경 변화 실시간 입체 관측 기술 미생물 활용 환경 복원 기술 미래 유망 기술 이중 기반 상황 인식 조광 기술 주변 상황 인식 자동 방향 세기 조절 공해 방지 범죄 예방 에너지 절약 가능 기술 능동 제어 소음 저감 기술 실시간 소음 발생 예측 소음 저감 기술 지하철 공항 고속도로 발생 소음 경주 지역 우리나라 심각 원전 사고 발생 우려 증가 로봇 인공 지능 활용 원전 사고 대응 시스템 마련 필요 비방 사성 비파 검사 기술 인체 환경 유해성 제거 테러 위험 방지 환경 변화 실시간 입체 관측 기술 인공 무인기 로봇 활용 환경 오염 생태계 변화 입체적 실시간 모니터링 분석 기술 미생물 활용 환경 복원 기술 원유 유출 인한 기름 음식물 쓰레기 처리 자원 유가 금속 추출 등과 환경 복원 미생물 활용 종화 부연 위원 경제 성장 중시 기존 정책 부작용 발생 생활 공해 환경 오염 문제 해결 하지 인류 지속 가능 발전 이번 발표 공해 오염 대응 미래 유망 기술 과학 기술 책임 강조 측면 의의 문병도 기자 서울 경제 바로가기 장미 전쟁 승자 대선 실황 중계 바로가기 서울 경제 확실 방법 페이스북\",\"split_begin_ends\":[{\"begin\":0,\"end\":22},{\"begin\":22,\"end\":39},{\"begin\":40,\"end\":101},{\"begin\":102,\"end\":159},{\"begin\":160,\"end\":222},{\"begin\":223,\"end\":308},{\"begin\":309,\"end\":483},{\"begin\":484,\"end\":574},{\"begin\":575,\"end\":671},{\"begin\":672,\"end\":763},{\"begin\":764,\"end\":922},{\"begin\":923,\"end\":1027},{\"begin\":1028,\"end\":1203},{\"begin\":1204,\"end\":1252},{\"begin\":1252,\"end\":1297}],\"text\":\"AI기반 팩트체킹, 미생물 환경복원...KISTEP 10대 미래유망기술\\n[서울경제] 클릭 한 번으로 수만 명에게 전달되는 특성 때문에 가짜뉴스로 인한 피해가 갈수록 확산 되고 있다. 그러나 앞으로는 실시간으로 사실 여부를 판별해 주는 인공지능(AI) 소프트웨어 기술이 등장할 전망이다.\\n한국과학기술기획평가원(KISTEP)은 20일 AI 기반의 팩트체킹 기술을 올해의 10대 미래유망기술로 선정했다. 이 기술은 2022년 각각 11억 달러, 186억 달러에 달할 것으로 추정되는 미디어 관련 인공지능 시장과 자연어 처리시장의 핵심이 될 것으로 예상했다.\\n또 사물인터넷(IoT) 기반 상황인식형 조광 기술 △능동제어형 소음 저감 기술 △원전사고 대응 시스템 △비방사성 비파괴 검사 기술 △초미세먼지 제거 기술 △친환경 녹조·적조 제거 기술 △생활폐기물 첨단 분류·재활용 시스템 △환경변화 실시간 입체 관측 기술 △미생물 활용 환경복원 기술 등을 미래유망기술로 뽑았다.\\n이중 IoT 기반 상황인식형 조광 기술은 주변 상황을 인식해 자동으로 빛의 방향과 세기를 조절하여 빛 공해 방지, 범죄 예방, 에너지 절약 등이 가능한 기술이다. 능동제어형 소음 저감 기술은 실시간으로 소음 발생을 예측한 후 능동적으로 소음을 저감시키는 기술로 지하철, 공항, 고속도로 등에서 발생하는 소음을 효과적으로 줄일 수 있다. 경주지역 지진으로 우리나라에서도 심각한 원전사고가 발생할 수 있다는 우려가 증가함에 따라 로봇, 인공지능 등을 활용한 원전사고 대응 시스템을 마련할 필요가 생겼다. 비방사성 비파괴 검사 기술은 인체 및 환경 유해성을 제거하고 핵테러 위험을 원천적으로 방지할 수 있다. 이 밖에 환경변화 실시간 입체 관측 기술은 IoT, 인공위성, 무인기, 로봇 등을 활용하여 환경오염 및 생태계의 변화를 입체적으로 실시간 모니터링하고 종합적으로 분석하는 기술이다. 미생물 활용 환경복원 기술은 원유 유출 사고로 인한 기름의 분해, 음식물 쓰레기의 친환경적 처리, 폐자원에서의 유가 금속 추출 등과 같이 환경을 복원하는데 미생물을 활용할 수 있게 한다.\\n박종화 KISTEP 부연구위원은 \\u201c경제 성장을 중시하는 기존 정책의 부작용으로 발생한 생활 공해와 환경오염 문제를 해결하지 않고서는 인류의 지속 가능한 발전이 불가능하다\\u201d며 \\u201c이번에 발표한 \\u2018공해·오염 대응 10대 미래유망기술은\\u2019 과학기술이 기여해야할 사회적 책임을 강조하는 측면에서 큰 의의가 있다\\u201d라고 밝혔다. /문병도기자 do@sedaily.com [서울경제 바로가기] \\u0027장미전쟁\\u0027의 승자는...5월 대선 실황중계 [바로가기] 서울경제와 친해지는 가장 확실한 방법 [페이스북]\",\"token_indexes\":[0,0,2,5,11,11,15,15,17,33,35,37,41,43,47,63,68,75,77,81,84,92,111,117,120,124,131,133,140,143,146,150,154,160,188,192,197,201,209,211,213,217,225,262,267,271,274,276,279,283,287,289,293,303,311,313,322,325,327,331,334,338,340,344,347,350,354,356,359,362,367,369,372,376,379,383,386,389,392,396,400,406,409,413,415,419,422,425,429,434,436,439,443,446,449,453,457,460,462,465,471,473,475,484,491,494,496,500,503,507,510,514,518,526,530,534,541,544,548,551,555,559,565,569,575,577,581,584,587,591,597,600,604,616,620,626,630,635,639,648,653,672,674,682,690,694,696,700,710,714,722,726,728,734,738,740,743,746,751,755,764,766,769,773,776,780,785,788,793,799,802,812,827,829,832,836,839,842,851,857,862,868,873,875,880,885,889,895,899,912,917,923,927,930,932,935,939,942,949,952,960,964,974,979,985,988,991,994,1000,1004,1010,1015,1029,1039,1042,1047,1050,1054,1059,1062,1066,1072,1076,1079,1083,1085,1088,1092,1094,1102,1106,1109,1113,1126,1130,1135,1138,1141,1148,1150,1152,1157,1159,1173,1177,1182,1189,1205,1208,1227,1229,1232,1239,1241,1246,1255,1258,1260,1264,1270,1272,1284,1288,1292]}";
        JSONObject obj = new JSONObject(json);

        String tokensValue = obj.getString("tokens");
        JSONArray token_indexes = obj.getJSONArray("token_indexes");
        int [] tokenIndexes = new int[token_indexes.length()];
        for (int i = 0; i <tokenIndexes.length ; i++) {
            tokenIndexes[i] = token_indexes.getInt(i);
        }

        System.out.println(json);


        JSONArray split_begin_ends =   obj.getJSONArray("split_begin_ends");
        BeginEndImpl[] splitBeginEnds = new BeginEndImpl[split_begin_ends.length()];

        for (int i = 0; i <splitBeginEnds.length ; i++) {
            JSONObject split_begin_end = split_begin_ends.getJSONObject(i);
            splitBeginEnds[i] = new BeginEndImpl(split_begin_end.getInt("begin"), split_begin_end.getInt("end"));
        }

        String [] tokens = tokensValue.split(" ");

        String [] keywords = {"AI기반","환경", "유망", "기술"};
//        String [] keywords = {"음식물", "쓰레기" , "자원"};
        String highlight = StringHighlight.make(obj.getString("text"), tokens, tokenIndexes, splitBeginEnds, keywords, "<en>", "</em>", 120);

        System.out.println(highlight);
    }
}
