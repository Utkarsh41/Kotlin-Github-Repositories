package com.utkarsh.githubkotlin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LanguageModel {
    @SerializedName("items")
    @Expose
    private List<LanguageItems>languageItemsList = null;

    public List<LanguageItems> getLanguageItemsList() {
        return languageItemsList;
    }

    public void setLanguageItemsList(List<LanguageItems> languageItemsList) {
        this.languageItemsList = languageItemsList;
    }
}
/*
@SerializedName("items")
    @Expose
    private List<Item> items;

    public List<Item> getItems(){
        return items;
    }
    public void setItems(List<Item>items){
        this.items = items;
    }
 */