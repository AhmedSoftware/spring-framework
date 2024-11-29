/*
 * Copyright 2002-2024 the original author or authors.
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

package org.springframework.docs.web.webflux.filters.urlhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.reactive.UrlHandlerFilter;

public class UrlHandlerFilterConfiguration {

	public void configureUrlHandlerFilter() {
		// tag::config[]
		UrlHandlerFilter urlHandlerFilter = UrlHandlerFilter
				// will HTTP 308 redirect "/blog/my-blog-post/" -> "/blog/my-blog-post"
				.trailingSlashHandler("/blog/**").redirect(HttpStatus.PERMANENT_REDIRECT)
				// will mutate the request to "/admin/user/account/" and make it as "/admin/user/account"
				.trailingSlashHandler("/admin/**").mutateRequest()
				.build();
		// end::config[]
	}
}
