package com.dgsoft.weedfs.demo.entity;

 
public  class WeedfsFileEntity {

    private String fid;
    private String name;
    private String url;
    private int size ;
  
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String toString() {
        return  "fid:"+this.getFid()+",name:"+this.getName()+",url:"+this.getUrl();
    }
    
 

}
