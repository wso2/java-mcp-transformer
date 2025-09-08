package org.wso2.carbon.mcp.transformer.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SchemaDefinition {
    @SerializedName("type")
    String type;

    @SerializedName("properties")
    Map<String, Map<String, String>> properties = new HashMap<>();

    @SerializedName("required")
    Set<String> required = new HashSet<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Map<String, String>> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Map<String, String>> properties) {
        this.properties = properties;
    }

    public Set<String> getRequired() {
        return required;
    }

    public void setRequired(Set<String> required) {
        this.required = required;
    }
}
