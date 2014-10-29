///*
// * Copyright 2005 Anders Nyman.
// * 
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * 
// *      http://www.apache.org/licenses/LICENSE-2.0
// * 
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package net.sf.j2ep.test;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//
//import net.sf.j2ep.ProxyFilter;
//
//import org.apache.cactus.FilterTestCase;
//import org.apache.cactus.WebRequest;
//import org.apache.cactus.WebResponse;
//
///**
// * Tests the OptionsHandler
// *
// * @author Anders Nyman
// */
//public class OptionTest extends FilterTestCase {
//    
//    private ProxyFilter proxyFilter;
//    
//    @Override
//    public void setUp() {
//        proxyFilter = new ProxyFilter();
//
//        config.setInitParameter("dataUrl", "/WEB-INF/classes/net/sf/j2ep/test/testData.xml");
//        try {
//            proxyFilter.init(config);
//        } catch (ServletException e) {
//            fail("Problem with init, error given was " + e.getMessage());
//        }
//    }
//    
//    public void beginNoMaxFowards(WebRequest theRequest) {
//        theRequest.setURL("localhost:8080", "/test", "/", null, null);
//    }
//
//    public void testNoMaxFowards() throws ServletException, IOException {
//        MethodWrappingRequest req = new MethodWrappingRequest(request, "OPTIONS");
//        proxyFilter.doFilter(req, response, filterChain);
//
//    }
//
//    public void endNoMaxFowards(WebResponse theResponse) {
//        assertEquals("Correct options not returned",
//                "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,", theResponse.getConnection()
//                        .getHeaderField("Allow"));
//    }
//
//    public void beginMaxForwards(WebRequest theRequest) {
//        theRequest.setURL("localhost:8080", "/test", "/maxForwards", null, null);
//        theRequest.addHeader("Max-Forwards", "0");
//    }
//
//    public void testMaxForwards() throws ServletException,
//            IOException {
//        MethodWrappingRequest req = new MethodWrappingRequest(request, "OPTIONS");
//        proxyFilter.doFilter(req, response, filterChain);
//    }
//    
//    public void endMaxForwards(WebResponse theResponse) {
//        assertEquals("Status code check", 200, theResponse.getStatusCode());
//        String allow = theResponse.getConnection().getHeaderField("allow");
//        /*
//         * Reason we have to check all and not just compare to string is
//         * that the order of the methods returned cannot is unknown and unimportant.
//         */
//        assertTrue("Should include OPTIONS", allow.contains("OPTIONS"));
//        assertTrue("Should include GET", allow.contains("GET"));
//        assertTrue("Should include HEAD", allow.contains("HEAD"));
//        assertTrue("Should include POST", allow.contains("POST"));
//        assertTrue("Should include PUT", allow.contains("PUT"));
//        assertTrue("Should include DELETE", allow.contains("DELETE"));
//        assertTrue("Should include TRACE", allow.contains("TRACE"));
//        assertEquals("Content Length should be 0", "0", theResponse.getConnection().getHeaderField("Content-Length"));
//    }
//    
//    public void beginServerWithUnsupportedMethods(WebRequest theRequest) {
//        theRequest.setURL("localhost:8080", "/test", "/testUnsupportedMethods", null, null);
//    }
//    
//    public void testServerWithUnsupportedMethods() throws ServletException, IOException {
//        MethodWrappingRequest req = new MethodWrappingRequest(request, "OPTIONS");
//        proxyFilter.doFilter(req, response, filterChain);
//    }
//    
//    public void endServerWithUnsupportedMethods(WebResponse theResponse) {
//        String allow = theResponse.getConnection().getHeaderField("Allow");
//
//        assertTrue("Should include OPTIONS", allow.contains("OPTIONS"));
//        assertTrue("Should include GET", allow.contains("GET"));
//        assertTrue("Should include HEAD", allow.contains("HEAD"));
//        assertTrue("Should include POST", allow.contains("POST"));
//        assertTrue("Should include DELETE", allow.contains("DELETE"));
//        assertTrue("Should include TRACE", allow.contains("TRACE"));
//        assertFalse("Shouldn't include PROPPATCH", allow.contains("PROPPATCH"));
//        assertFalse("Shouldn't include COPY", allow.contains("COPY"));
//        assertFalse("Shouldn't include MOVE", allow.contains("MOVE"));
//        assertFalse("Shouldn't include LOCK", allow.contains("LOCK"));
//        assertFalse("Shouldn't include UNLOCK", allow.contains("UNLOCK"));
//        assertFalse("Shouldn't include PROPFIND", allow.contains("PROPFIND"));
//    }
//}