package org.wso2.carbon.mcp.transformer.impl;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.mcp.transformer.exception.SchemaMappingException;
import org.wso2.carbon.mcp.transformer.model.Param;
import org.wso2.carbon.mcp.transformer.model.SchemaDefinition;
import org.wso2.carbon.mcp.transformer.model.SchemaMapping;
import org.wso2.carbon.mcp.transformer.model.SchemaMappingParser;
import org.wso2.carbon.mcp.transformer.util.ResolverConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrefixBasedSchemaMappingParser implements SchemaMappingParser {
    private static final Log log = LogFactory.getLog(PrefixBasedSchemaMappingParser.class);

    @Override
    public SchemaMapping parse(Object schema) throws SchemaMappingException {
        if (!(schema instanceof String)) {
            throw new SchemaMappingException("Unsupported schema type: " + schema.getClass().getName());
        }

        return parseMcpSchema((String) schema);
    }

    /**
     * Parses the MCP schema definition string and maps it to a SchemaMapping object.
     *
     * @param schemaDefinitionString the schema definition string in JSON format
     * @return the mapped schema
     */
    private static SchemaMapping parseMcpSchema(String schemaDefinitionString) {
        //Convert the schema definition string to a SchemaDefinition object
        Gson gson = new Gson();
        SchemaDefinition schemaDefinition = gson.fromJson(schemaDefinitionString, SchemaDefinition.class);

        return processSchemaProperties(schemaDefinition);
    }

    /**
     * Processes schema properties and maps them to query, header, and path parameters.
     *
     * @param schemaDefinition the schema definition to process
     * @return the mapped schema
     */
    private static SchemaMapping processSchemaProperties(SchemaDefinition schemaDefinition) {
        Map<String, Map<String, String>> properties = schemaDefinition.getProperties();

        Set<String> required = schemaDefinition.getRequired();

        SchemaMapping schemaMapping = new SchemaMapping();
        List<Param> queryParams = new ArrayList<>();
        List<Param> headerParams = new ArrayList<>();
        List<Param> pathParams = new ArrayList<>();

        for (Map.Entry<String, Map<String, String>> entry : properties.entrySet()) {
            String key = entry.getKey();
            Map<String, String> value = entry.getValue();

            if (ResolverConstants.REQUEST_BODY_KEY.equalsIgnoreCase(key)) {
                String contentType = value.get(ResolverConstants.CONTENT_TYPE_KEY);
                schemaMapping.setContentType(contentType);
                schemaMapping.setHasBody(true);
                continue;
            }

            String[] meta = key.split("_", 2);
            if (meta.length < 2) {
                // If the key does not follow the expected format, skip it
                if (log.isDebugEnabled()) {
                    log.debug("Skipping key: " + key + " as it does not follow the expected format");
                }
                continue;
            }

            String paramType = meta[0];
            String paramName = meta[1];
            boolean isRequired = required.contains(key);

            switch(paramType) {
                case "query":
                    queryParams.add(new Param(paramName, value.get(ResolverConstants.FORMAT_KEY),
                            value.get(ResolverConstants.DESCRIPTION_KEY), isRequired));
                    break;
                case "header":
                    headerParams.add(new Param(paramName, value.get(ResolverConstants.FORMAT_KEY),
                            value.get(ResolverConstants.DESCRIPTION_KEY), isRequired));
                    break;
                case "path":
                    pathParams.add(new Param(paramName, value.get(ResolverConstants.FORMAT_KEY),
                            value.get(ResolverConstants.DESCRIPTION_KEY), isRequired));
                    break;
                default:
                    if (log.isDebugEnabled()) {
                        log.debug("Unknown parameter type: " + paramType + " for key: " + key);
                    }
                    break;
            }
        }

        schemaMapping.setQueryParams(queryParams);
        schemaMapping.setHeaderParams(headerParams);
        schemaMapping.setPathParams(pathParams);
        return schemaMapping;
    }

}
