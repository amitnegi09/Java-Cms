/**
 * Copyright 2010-2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onehippo.gogreen.components.jobs;

import com.onehippo.gogreen.beans.JobsDocument;
import com.onehippo.gogreen.components.BaseComponent;
import com.onehippo.gogreen.components.ComponentUtil;
import com.onehippo.gogreen.utils.Constants;
import com.onehippo.gogreen.utils.GoGreenUtil;
import com.onehippo.gogreen.utils.PageableCollection;

import org.apache.commons.lang.StringEscapeUtils;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.core.request.ResolvedSiteMapItem;
import org.hippoecm.hst.util.ContentBeanUtils;
import org.hippoecm.hst.util.SearchInputParsingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Overview extends BaseComponent {


    private static final Logger log = LoggerFactory.getLogger(Overview.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        final HstRequestContext ctx = request.getRequestContext();

        String currentPageParam = getPublicRequestParameter(request, Constants.PAGE);
        int pageNumber = ComponentUtil.parseIntParameter(Constants.PAGE, currentPageParam, Constants.DEFAULT_PAGE_NUMBER, log);

        String query = this.getPublicRequestParameter(request, "query");
        query = SearchInputParsingUtils.parse(query, false);
        request.setAttribute("query", StringEscapeUtils.escapeHtml(query));

        HippoBean currentBean = ctx.getContentBean();
        if (currentBean == null) {
            ResolvedSiteMapItem resolvedSiteMapItem = request.getRequestContext().getResolvedSiteMapItem();
            log.warn("Content bean not found; please check the relative content path for sitemap item: {}. Relative content path: {}.",
                    resolvedSiteMapItem.getHstSiteMapItem().getId(),
                    resolvedSiteMapItem.getRelativeContentPath());
            return;
        }

        HippoFacetNavigationBean facetBean = ContentBeanUtils.getFacetNavigationBean(query);
        if (facetBean == null) {
            facetBean = ContentBeanUtils.getFacetNavigationBean("jobfacets", query);
        }

        request.setAttribute("docs", new PageableCollection(facetBean.getResultSet().getDocumentIterator(JobsDocument.class), facetBean.getCount().intValue(), GoGreenUtil.getIntConfigurationParameter(request,
                Constants.PAGE_SIZE, Constants.DEFAULT_PAGE_SIZE), pageNumber));
        request.setAttribute("count", facetBean.getCount());
    }
}