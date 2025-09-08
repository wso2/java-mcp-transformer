/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.mcp.transformer;

import java.util.Map;

/**
 * Test class for RequestResolver.
 */
public class RequestResolverTest {
    Map<String, Object> inputArgumentsForBasicSchema = Map.of("id", 123, "query", "name:John", "OrgID" , 456,
            "requestBody", Map.of("firstName", "John", "lastName", "Doe"));

//    @Test
//    public void testResolveRequest() throws Exception{
//        String schemaJson = IOUtils.toString(Objects.requireNonNull(
//                getClass().getClassLoader().getResourceAsStream("basic-schema.json")), StandardCharsets.UTF_8
//        );
//        PrefixBasedSchemaMappingParser parser = new PrefixBasedSchemaMappingParser();
//        RequestResolver requestResolver = new RequestResolver(parser);
//
//        ResolvedRequest resolvedRequest = requestResolver.resolve(schemaJson, inputArgumentsForBasicSchema);
//
//        Map<String, Object> pathParams = resolvedRequest.getPathParams();
//        Assert.assertNotNull(pathParams);
//        Assert.assertEquals(1, pathParams.size());
//        Assert.assertEquals("123", pathParams.get("id"));
//
//        Map<String, Object> queryParams = resolvedRequest.getQueryParams();
//        Assert.assertNotNull(queryParams);
//        Assert.assertEquals(1, queryParams.size());
//        Assert.assertEquals("name%3AJohn", queryParams.get("query"));
//
//        Map<String, Object> headerParams = resolvedRequest.getHeaderParams();
//        Assert.assertNotNull(headerParams);
//        Assert.assertEquals(1, headerParams.size());
//        Assert.assertEquals("456", headerParams.get("OrgID"));
//    }
}
