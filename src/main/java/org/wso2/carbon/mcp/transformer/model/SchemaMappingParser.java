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

package org.wso2.carbon.mcp.transformer.model;

import org.wso2.carbon.mcp.transformer.exception.SchemaMappingException;

/**
 * Interface for parsing schema definitions and generating SchemaMapping objects.
 */
public interface SchemaMappingParser {

    /**
     * Parse the given schema and return the SchemaMapping object.
     *
     * @param schema The schema to be parsed.
     * @return The SchemaMapping object.
     * @throws SchemaMappingException If an error occurs while parsing the schema.
     */
    SchemaMapping parse(Object schema) throws SchemaMappingException;

}
