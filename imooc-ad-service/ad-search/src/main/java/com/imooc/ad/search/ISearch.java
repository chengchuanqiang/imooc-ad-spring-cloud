package com.imooc.ad.search;

import com.imooc.ad.search.vo.SearchRequest;
import com.imooc.ad.search.vo.SearchResponse;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/22 23:18
 */
public interface ISearch {

    SearchResponse fetchAds(SearchRequest request);

}
