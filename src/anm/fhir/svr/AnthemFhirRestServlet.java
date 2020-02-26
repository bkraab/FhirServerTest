package anm.fhir.svr;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anm.refpoc.fhir.ResProvs.*;

/**
 * Servlet implementation class IhsRestServlet
 */
@WebServlet(urlPatterns= {"/fhir/*"}, displayName="FHIR Server")
public class AnthemFhirRestServlet extends RestfulServer {
	private static final long serialVersionUID = 1L;
	
	 @Override
	    protected void initialize() throws ServletException {		 
	    	List<IResourceProvider> resourceProviders = new ArrayList<IResourceProvider>();
	    	resourceProviders.add(new AllergyIntoleranceRestfulProvider());
	    	resourceProviders.add(new ClaimResourceRestfulProvider());
	    	resourceProviders.add(new ConditionResourceRestfulProvider());
	    	resourceProviders.add(new EncounterResourceRestfulProvider());	    	
	    	resourceProviders.add(new OrganizationResourceRestfulProvider());
	    	resourceProviders.add(new PatientResourceRestfulProvider());
	    	resourceProviders.add(new PractitionerResourceRestfulProvider());
	    	resourceProviders.add(new ProcedureResourceRestfulProvider());
	    	
	    	setResourceProviders(resourceProviders);
	                    
	    }
       
    /**
     * @see RestfulServer#RestfulServer()
     */
//    public AnthemFhirRestServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//       
//    /**
//     * @see RestfulServer#RestfulServer(FhirContext)
//     */
//    public AnthemFhirRestServlet(FhirContext theCtx) {
//        super(theCtx);
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
//	 */
//	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
//	 */
//	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

}

