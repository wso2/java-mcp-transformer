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

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.wso2.carbon.mcp.transformer.impl.PrefixBasedSchemaMappingParser;
import org.wso2.carbon.mcp.transformer.model.Param;
import org.wso2.carbon.mcp.transformer.model.SchemaMapping;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * Test class for PrefixBasedSchemaMappingParser.
 */
public class PrefixBasedSchemaMappingParserTest {

    @Test
    public void testParseSchemaMapping() throws Exception {
        String schemaJson = IOUtils.toString(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("basic-schema.json")), StandardCharsets.UTF_8
        );
        PrefixBasedSchemaMappingParser parser = new PrefixBasedSchemaMappingParser();
        SchemaMapping parsedSchemaMapping = parser.parse(schemaJson);

        Assert.assertNotNull(parsedSchemaMapping);
        //verify Path params
        List<Param> pathParams = parsedSchemaMapping.getPathParams();
        Assert.assertEquals(1, pathParams.size());
        Assert.assertEquals("id", pathParams.get(0).getName());
        Assert.assertTrue(pathParams.get(0).isRequired());

        //verify Query params
        List<Param> queryParams = parsedSchemaMapping.getQueryParams();
        Assert.assertEquals(1, queryParams.size());
        Assert.assertEquals("query", queryParams.get(0).getName());
        Assert.assertFalse(queryParams.get(0).isRequired());

        //verify Header params
        List<Param> headerParams = parsedSchemaMapping.getHeaderParams();
        Assert.assertEquals(1, headerParams.size());
        Assert.assertEquals("OrgID", headerParams.get(0).getName());
        Assert.assertFalse(headerParams.get(0).isRequired());

        //verify Body
        Assert.assertTrue(parsedSchemaMapping.isHasBody());
        Assert.assertEquals("application/json", parsedSchemaMapping.getContentType());
    }

    @Test
    public void testParseSchemaWithNoRequestBody() throws Exception {
        String schemaJson = IOUtils.toString(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("schema-no-request-body.json")), StandardCharsets.UTF_8
        );
        PrefixBasedSchemaMappingParser parser = new PrefixBasedSchemaMappingParser();
        SchemaMapping parsedSchemaMapping = parser.parse(schemaJson);

        Assert.assertNotNull(parsedSchemaMapping);
        //verify Path params
        List<Param> pathParams = parsedSchemaMapping.getPathParams();
        Assert.assertEquals(1, pathParams.size());

        //verify Query params
        List<Param> queryParams = parsedSchemaMapping.getQueryParams();
        Assert.assertEquals(1, queryParams.size());

        //verify Header params
        List<Param> headerParams = parsedSchemaMapping.getHeaderParams();
        Assert.assertEquals(1, headerParams.size());;

        //verify Body
        Assert.assertFalse(parsedSchemaMapping.isHasBody());
    }

    @Test
    public void testParseSchemaWithNoParams() throws Exception {
        String schemaJson = IOUtils.toString(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("schema-no-params.json")), StandardCharsets.UTF_8
        );
        PrefixBasedSchemaMappingParser parser = new PrefixBasedSchemaMappingParser();
        SchemaMapping parsedSchemaMapping = parser.parse(schemaJson);

        Assert.assertNotNull(parsedSchemaMapping);
        //verify Path params
        List<Param> pathParams = parsedSchemaMapping.getPathParams();
        Assert.assertEquals(0, pathParams.size());

        //verify Query params
        List<Param> queryParams = parsedSchemaMapping.getQueryParams();
        Assert.assertEquals(0, queryParams.size());

        //verify Header params
        List<Param> headerParams = parsedSchemaMapping.getHeaderParams();
        Assert.assertEquals(0, headerParams.size());;

        //verify Body
        Assert.assertTrue(parsedSchemaMapping.isHasBody());
        Assert.assertEquals("application/json", parsedSchemaMapping.getContentType());
    }

    @Test
    public void testParseEmptySchema() throws Exception {
        String schemaJson = IOUtils.toString(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("empty-schema.json")), StandardCharsets.UTF_8
        );
        PrefixBasedSchemaMappingParser parser = new PrefixBasedSchemaMappingParser();
        SchemaMapping parsedSchemaMapping = parser.parse(schemaJson);

        Assert.assertNotNull(parsedSchemaMapping);
        //verify Path params
        List<Param> pathParams = parsedSchemaMapping.getPathParams();
        Assert.assertEquals(0, pathParams.size());

        //verify Query params
        List<Param> queryParams = parsedSchemaMapping.getQueryParams();
        Assert.assertEquals(0, queryParams.size());

        //verify Header params
        List<Param> headerParams = parsedSchemaMapping.getHeaderParams();
        Assert.assertEquals(0, headerParams.size());;

        //verify Body
        Assert.assertFalse(parsedSchemaMapping.isHasBody());
    }
}
