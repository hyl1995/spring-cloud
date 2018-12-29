package com.example.core.config;

public class ParamConfig {
    private String appKey;

    private String appSecret;

    private String bucket;

    private String endPoint;

    public static class Builder{

        private String appKey;

        private String appSecret;

        private String bucket;

        private String endPoint;

        public Builder setAppKey(String appKey){
            this.appKey = appKey;
            return this;
        }

        public Builder setAppSecret(String appSecret){
            this.appSecret = appSecret;
            return this;
        }

        public Builder setBucket(String bucket){
            this.bucket = bucket;
            return this;
        }

        public Builder setEndPoint(String endPoint){
            this.endPoint = endPoint;
            return this;
        }

        public ParamConfig build(){
            return new ParamConfig(this);
        }
    }

    public static Builder options(){
        return new ParamConfig.Builder();
    }

    private ParamConfig(Builder builder){
        this.appKey = builder.appKey;
        this.appSecret = builder.appSecret;
        this.bucket = builder.bucket;
        this.endPoint = builder.endPoint;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getBucket() {
        return bucket;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
