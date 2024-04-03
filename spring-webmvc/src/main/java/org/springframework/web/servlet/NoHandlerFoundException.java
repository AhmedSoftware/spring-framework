/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

/**
 * Since 6.1: When the DispatcherServlet can't find a handler for a request, this 
 * exception is raised and may be handled with a configured HandlerExceptionResolver. 
 * However, if its (depricated) property "throwExceptionIfNoHandlerFound"
 * is set to {@code false} it sends a 404 response.
 * 
 * <p><strong>Note:</strong> until 6.1 the default was to set throwExceptionIfNoHandlerFound to {@code false} and send a 404 response.
 *
 * @author Brian Clozel
 * @since 4.0
 * @see DispatcherServlet#setThrowExceptionIfNoHandlerFound(boolean)
 * @see DispatcherServlet#noHandlerFound(HttpServletRequest, HttpServletResponse)
 */
@SuppressWarnings("serial")
public class NoHandlerFoundException extends ServletException implements ErrorResponse {

	private final String httpMethod;

	private final String requestURL;

	private final HttpHeaders requestHeaders;

	private final ProblemDetail body;


	/**
	 * Constructor for NoHandlerFoundException.
	 * @param httpMethod the HTTP method
	 * @param requestURL the HTTP request URL
	 * @param headers the HTTP request headers
	 */
	public NoHandlerFoundException(String httpMethod, String requestURL, HttpHeaders headers) {
		super("No endpoint " + httpMethod + " " + requestURL + ".");
		this.httpMethod = httpMethod;
		this.requestURL = requestURL;
		this.requestHeaders = headers;
		this.body = ProblemDetail.forStatusAndDetail(getStatusCode(), getMessage());
	}

	@Override
	public HttpStatusCode getStatusCode() {
		return HttpStatus.NOT_FOUND;
	}

	public String getHttpMethod() {
		return this.httpMethod;
	}

	public String getRequestURL() {
		return this.requestURL;
	}

	/**
	 * Return headers to use for the response.
	 * <p><strong>Note:</strong> As of 6.0 this method overlaps with
	 * {@link ErrorResponse#getHeaders()} and therefore no longer returns request
	 * headers. Use {@link #getRequestHeaders()} instead for request headers.
	 */
	@Override
	public HttpHeaders getHeaders() {
		return ErrorResponse.super.getHeaders();
	}

	/**
	 * Return the headers of the request.
	 * @since 6.0.3
	 */
	public HttpHeaders getRequestHeaders() {
		return this.requestHeaders;
	}

	@Override
	public ProblemDetail getBody() {
		return this.body;
	}

}
