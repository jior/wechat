package com.glaf.wechat.web.springmvc;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import com.alibaba.fastjson.*;

import com.glaf.core.config.ViewProperties;
import com.glaf.core.identity.*;
import com.glaf.core.security.*;
import com.glaf.core.util.*;

import com.glaf.wechat.domain.*;
import com.glaf.wechat.query.*;
import com.glaf.wechat.service.*;

@Controller("/wx/wxProduct")
@RequestMapping("/wx/wxProduct")
public class WxProductController {
	protected static final Log logger = LogFactory
			.getLog(WxProductController.class);

	protected WxProductService wxProductService;

	public WxProductController() {

	}

	@javax.annotation.Resource
	public void setWxProductService(WxProductService wxProductService) {
		this.wxProductService = wxProductService;
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, ModelMap modelMap) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxProduct wxProduct = new WxProduct();
		Tools.populate(wxProduct, params);

		wxProduct.setName(request.getParameter("name"));
		wxProduct.setPrice(RequestUtils.getDouble(request, "price"));
		wxProduct.setNewsNum(RequestUtils.getInt(request, "newsNum"));
		wxProduct.setCategoryNum(RequestUtils.getInt(request, "categoryNum"));
		wxProduct.setDayNum(RequestUtils.getInt(request, "dayNum"));
		wxProduct.setCreateBy(request.getParameter("createBy"));
		wxProduct.setCreateDate(RequestUtils.getDate(request, "createDate"));

		wxProduct.setCreateBy(actorId);

		wxProductService.save(wxProduct);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/saveWxProduct")
	public byte[] saveWxProduct(HttpServletRequest request) {
		User user = RequestUtils.getUser(request);
		String actorId = user.getActorId();
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxProduct wxProduct = new WxProduct();
		try {
			Tools.populate(wxProduct, params);
			wxProduct.setName(request.getParameter("name"));
			wxProduct.setPrice(RequestUtils.getDouble(request, "price"));
			wxProduct.setNewsNum(RequestUtils.getInt(request, "newsNum"));
			wxProduct.setCategoryNum(RequestUtils
					.getInt(request, "categoryNum"));
			wxProduct.setDayNum(RequestUtils.getInt(request, "dayNum"));
			wxProduct.setCreateBy(request.getParameter("createBy"));
			wxProduct
					.setCreateDate(RequestUtils.getDate(request, "createDate"));
			wxProduct.setCreateBy(actorId);
			this.wxProductService.save(wxProduct);

			return ResponseUtils.responseJsonResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		}
		return ResponseUtils.responseJsonResult(false);
	}

	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		params.remove("status");
		params.remove("wfStatus");

		WxProduct wxProduct = wxProductService.getWxProduct(RequestUtils
				.getLong(request, "id"));

		wxProduct.setName(request.getParameter("name"));
		wxProduct.setPrice(RequestUtils.getDouble(request, "price"));
		wxProduct.setNewsNum(RequestUtils.getInt(request, "newsNum"));
		wxProduct.setCategoryNum(RequestUtils.getInt(request, "categoryNum"));
		wxProduct.setDayNum(RequestUtils.getInt(request, "dayNum"));
		wxProduct.setCreateBy(request.getParameter("createBy"));
		wxProduct.setCreateDate(RequestUtils.getDate(request, "createDate"));

		wxProductService.save(wxProduct);

		return this.list(request, modelMap);
	}

	@ResponseBody
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, ModelMap modelMap) {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Long id = RequestUtils.getLong(request, "id");
		String ids = request.getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			StringTokenizer token = new StringTokenizer(ids, ",");
			while (token.hasMoreTokens()) {
				String x = token.nextToken();
				if (StringUtils.isNotEmpty(x)) {
					WxProduct wxProduct = wxProductService.getWxProduct(Long
							.valueOf(x));
					/**
					 * 此处业务逻辑需自行调整
					 */
					if (wxProduct != null
							&& (StringUtils.equals(wxProduct.getCreateBy(),
									loginContext.getActorId()) || loginContext
									.isSystemAdministrator())) {

					}
				}
			}
		} else if (id != null) {
			WxProduct wxProduct = wxProductService.getWxProduct(Long
					.valueOf(id));
			/**
			 * 此处业务逻辑需自行调整
			 */
			if (wxProduct != null
					&& (StringUtils.equals(wxProduct.getCreateBy(),
							loginContext.getActorId()) || loginContext
							.isSystemAdministrator())) {

			}
		}
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		request.removeAttribute("canSubmit");
		WxProduct wxProduct = wxProductService.getWxProduct(RequestUtils
				.getLong(request, "id"));
		if (wxProduct != null) {
			request.setAttribute("wxProduct", wxProduct);
			JSONObject rowJSON = wxProduct.toJsonObject();
			request.setAttribute("x_json", rowJSON.toJSONString());
		}

		boolean canUpdate = false;
		String x_method = request.getParameter("x_method");
		if (StringUtils.equals(x_method, "submit")) {

		}

		request.setAttribute("canUpdate", canUpdate);

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		String x_view = ViewProperties.getString("wxProduct.edit");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}

		return new ModelAndView("/wx/wxProduct/edit", modelMap);
	}

	@RequestMapping("/view")
	public ModelAndView view(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		WxProduct wxProduct = wxProductService.getWxProduct(RequestUtils
				.getLong(request, "id"));
		request.setAttribute("wxProduct", wxProduct);
		JSONObject rowJSON = wxProduct.toJsonObject();
		request.setAttribute("x_json", rowJSON.toJSONString());

		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view);
		}

		String x_view = ViewProperties.getString("wxProduct.view");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view);
		}

		return new ModelAndView("/wx/wxProduct/view");
	}

	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}
		String x_view = ViewProperties.getString("wxProduct.query");
		if (StringUtils.isNotEmpty(x_view)) {
			return new ModelAndView(x_view, modelMap);
		}
		return new ModelAndView("/wx/wxProduct/query", modelMap);
	}

	@RequestMapping("/json")
	@ResponseBody
	public byte[] json(HttpServletRequest request, ModelMap modelMap)
			throws IOException {
		LoginContext loginContext = RequestUtils.getLoginContext(request);
		Map<String, Object> params = RequestUtils.getParameterMap(request);
		WxProductQuery query = new WxProductQuery();
		Tools.populate(query, params);
		query.deleteFlag(0);
		query.setActorId(loginContext.getActorId());
		query.setLoginContext(loginContext);
		/**
		 * 此处业务逻辑需自行调整
		 */
		if (!loginContext.isSystemAdministrator()) {
			String actorId = loginContext.getActorId();
			query.createBy(actorId);
		}

		String gridType = ParamUtils.getString(params, "gridType");
		if (gridType == null) {
			gridType = "easyui";
		}
		int start = 0;
		int limit = 10;
		String orderName = null;
		String order = null;

		int pageNo = ParamUtils.getInt(params, "page");
		limit = ParamUtils.getInt(params, "rows");
		start = (pageNo - 1) * limit;
		orderName = ParamUtils.getString(params, "sortName");
		order = ParamUtils.getString(params, "sortOrder");

		if (start < 0) {
			start = 0;
		}

		if (limit <= 0) {
			limit = Paging.DEFAULT_PAGE_SIZE;
		}

		JSONObject result = new JSONObject();
		int total = wxProductService.getWxProductCountByQueryCriteria(query);
		if (total > 0) {
			result.put("total", total);
			result.put("totalCount", total);
			result.put("totalRecords", total);
			result.put("start", start);
			result.put("startIndex", start);
			result.put("limit", limit);
			result.put("pageSize", limit);

			if (StringUtils.isNotEmpty(orderName)) {
				query.setSortOrder(orderName);
				if (StringUtils.equals(order, "desc")) {
					query.setSortOrder(" desc ");
				}
			}

			List<WxProduct> list = wxProductService
					.getWxProductsByQueryCriteria(start, limit, query);

			if (list != null && !list.isEmpty()) {
				JSONArray rowsJSON = new JSONArray();

				result.put("rows", rowsJSON);

				for (WxProduct wxProduct : list) {
					JSONObject rowJSON = wxProduct.toJsonObject();
					rowJSON.put("id", wxProduct.getId());
					rowJSON.put("wxProductId", wxProduct.getId());
					rowJSON.put("startIndex", ++start);
					rowsJSON.add(rowJSON);
				}

			}
		} else {
			JSONArray rowsJSON = new JSONArray();
			result.put("rows", rowsJSON);
			result.put("total", total);
		}
		return result.toJSONString().getBytes("UTF-8");
	}

	@RequestMapping
	public ModelAndView list(HttpServletRequest request, ModelMap modelMap) {
		RequestUtils.setRequestParameterToAttribute(request);
		String x_query = request.getParameter("x_query");
		if (StringUtils.equals(x_query, "true")) {
			Map<String, Object> paramMap = RequestUtils
					.getParameterMap(request);
			String x_complex_query = JsonUtils.encode(paramMap);
			x_complex_query = RequestUtils.encodeString(x_complex_query);
			request.setAttribute("x_complex_query", x_complex_query);
		} else {
			request.setAttribute("x_complex_query", "");
		}
		String view = request.getParameter("view");
		if (StringUtils.isNotEmpty(view)) {
			return new ModelAndView(view, modelMap);
		}

		return new ModelAndView("/wx/wxProduct/list", modelMap);
	}

}
