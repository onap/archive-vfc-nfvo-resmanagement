
package org.onap.vfc.nfvo.resmanagement.service.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.onap.vfc.nfvo.resmanagement.common.constant.HttpConstant;
import org.onap.vfc.nfvo.resmanagement.common.constant.ParamConstant;
import org.onap.vfc.nfvo.resmanagement.common.constant.UrlConstant;
import org.onap.vfc.nfvo.resmanagement.common.util.request.RequestUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.response.ResponseUtil;
import org.onap.vfc.nfvo.resmanagement.common.util.response.RoaResponseUtil;
import org.onap.vfc.nfvo.resmanagement.service.entity.NsEntity;
import org.onap.vfc.nfvo.resmanagement.service.group.inf.NsService;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

@Path(UrlConstant.NS_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NsRoa {

    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualLinkRoa.class);

    private NsService nsService;

    /**
     * <br>
     * 
     * @param context
     * @param resp
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    @POST
    public JSONObject addNs(@Context HttpServletRequest context, @Context HttpServletResponse resp)
            throws ServiceException {
        JSONObject object = RequestUtil.getJsonRequestBody(context);
        JSONObject restJson = new JSONObject();
        if(null == object) {
            LOGGER.error("function=addNs; msg=add error, because ns is null.");
            restJson.put("message", "ns is null");
            resp.setStatus(HttpConstant.HTTP_BAD_REQUEST);
            return restJson;
        }

        LOGGER.info("VnfRoa::addVnf:{}", object.toString());
        return nsService.addNs(object);
    }

    /**
     * <br>
     * 
     * @param context
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    @GET
    public JSONObject getAllNs(@Context HttpServletRequest context) throws ServiceException {
        Map<String, Object> map = new HashMap<>(10);
        List<NsEntity> allNs = nsService.getList(map);
        LOGGER.info("NsRoa::getAllNs:{}", allNs.toString());
        JSONObject result = new JSONObject();
        result.put("ns", allNs);
        return result;
    }

    /**
     * <br>
     * 
     * @param context
     * @param id
     * @return
     * @throws ServiceException
     * @since VFC 1.0
     */
    @GET
    @Path("/{nsId}")
    public JSONObject getNs(@Context HttpServletRequest context, @PathParam("nsId") String id) throws ServiceException {
        LOGGER.info("NsRoa::getNs id:{}", id);
        Map<String, Object> map = new HashMap<>(10);
        map.put(ParamConstant.PARAM_ID, id);
        List<NsEntity> ns = nsService.getList(map);
        LOGGER.info("NsRoa::getNs:{}", ns.toString());
        JSONObject result = new JSONObject();
        result.put("ns", ns.get(0));
        return result;
    }

    @DELETE
    @Path("/{id}")
    public JSONObject deleteNs(@Context HttpServletRequest context, @Context HttpServletResponse resp,
            @PathParam(ParamConstant.PARAM_ID) String id) throws ServiceException {
        JSONObject restJson = new JSONObject();
        if(id == null) {
            LOGGER.error("function=deleteNs; msg=delete error, because id is null.");
            restJson.put("message", "ns id is null");
            resp.setStatus(HttpConstant.HTTP_BAD_REQUEST);
            return restJson;
        }
        LOGGER.info("NsRoa::deleteNs id:{}", id);
        try {
            int result = nsService.delete(id);
            return RoaResponseUtil.delete(result);
        } catch(ServiceException se) {
            LOGGER.error("NsRoa::deleteNs error:{}" + se);
            return ResponseUtil.genHttpResponse(HttpConstant.ERROR_CODE, se.getMessage());
        }
    }

    public void setNsService(NsService nsService) {
        this.nsService = nsService;
    }
}
