package LanguageDetection.domain.factories;

import LanguageDetection.domain.entities.BlackList;

import java.net.MalformedURLException;

public interface IBlackListFactory {

    public BlackList createBlackListItem(String url) throws MalformedURLException;

}
