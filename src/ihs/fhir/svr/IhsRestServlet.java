package ihs.fhir.svr;

import java.util.ArrayList;
import java.util.List;

//import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.*;
//import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import ihs.fhir.ResProvs.*;




/**
 * Servlet implementation class IhsRestServlet
 */
//@WebServlet(urlPatterns= {"/fhir/*"}, displayName="FHIR Server")
public class IhsRestServlet{// extends RestfulServer {
//	private static final long serialVersionUID = 1L;
//	
//	 @Override
//	    protected void initialize() throws ServletException {
//		 	List<IResourceProvider> resourceProviders = new ArrayList<IResourceProvider>();
//		 	resourceProviders.add(new RestfulAllergyIntoleranceProvider());
//		 	resourceProviders.add(new RestfulClaimResourceProvider());		 	
//		 	resourceProviders.add(new RestfulConditionResourceProvider());
//		 	resourceProviders.add(new RestfulEncounterResourceProvider());
//		 	resourceProviders.add(new RestfulOrganizationResourceProvider());
//		 	resourceProviders.add(new RestfulPatientResourceProvider());
//		 	resourceProviders.add(new RestfulPractitionerResourceProvider());	
//		 	resourceProviders.add(new RestfulProcedureResourceProvider());		 			 	
//	    	setResourceProviders(resourceProviders);	                    
//	    }
       
//    /**
//     * @see RestfulServer#RestfulServer()
//     */
//    public IhsRestServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//       
//    /**
//     * @see RestfulServer#RestfulServer(FhirContext)
//     */
//    public IhsRestServlet(FhirContext theCtx) {
//        super(theCtx);
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
//	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
//	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

}
