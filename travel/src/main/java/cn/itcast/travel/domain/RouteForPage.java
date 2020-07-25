package cn.itcast.travel.domain;

import java.util.List;

public class RouteForPage {
    private Integer nowPage;
    private Integer cidForPage;
    private Integer numberOfPage;
    private Integer numberOfOnePage;
    private Integer allNumberOfFFind;
    private List<Route> listOfRoute;
    private String searchKey;

    public RouteForPage(Integer nowPage, Integer cidForPage, Integer numberOfPage, Integer numberOfOnePage, Integer allNumberOfFFind, List<Route> listOfRoute, String searchKey) {

        this.nowPage = nowPage;
        this.cidForPage = cidForPage;
        this.numberOfPage = numberOfPage;
        this.numberOfOnePage = numberOfOnePage;
        this.allNumberOfFFind = allNumberOfFFind;
        this.listOfRoute = listOfRoute;
        this.searchKey = searchKey;
    }

    public RouteForPage(Integer nowPage, Integer cidForPage, Integer numberOfOnePage, String searchKey) {
        this.nowPage = nowPage;
        this.cidForPage = cidForPage;
        this.numberOfOnePage = numberOfOnePage;
        this.searchKey = searchKey;
    }

    @Override
    public String toString() {
        return "RouteForPage{" +
                "nowPage=" + nowPage +
                ", cidForPage=" + cidForPage +
                ", numberOfPage=" + numberOfPage +
                ", numberOfOnePage=" + numberOfOnePage +
                ", allNumberOfFFind=" + allNumberOfFFind +
                ", listOfRoute=" + listOfRoute +
                ", searchKey='" + searchKey + '\'' +
                '}';
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getCidForPage() {
        return cidForPage;
    }

    public void setCidForPage(Integer cidForPage) {
        this.cidForPage = cidForPage;
    }

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public Integer getNumberOfOnePage() {
        return numberOfOnePage;
    }

    public void setNumberOfOnePage(Integer numberOfOnePage) {
        this.numberOfOnePage = numberOfOnePage;
    }

    public Integer getAllNumberOfFFind() {
        return allNumberOfFFind;
    }

    public void setAllNumberOfFFind(Integer allNumberOfFFind) {
        this.allNumberOfFFind = allNumberOfFFind;
    }

    public List<Route> getListOfRoute() {
        return listOfRoute;
    }

    public void setListOfRoute(List<Route> listOfRoute) {
        this.listOfRoute = listOfRoute;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public RouteForPage() {
    }
}
