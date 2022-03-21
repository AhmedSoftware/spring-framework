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

package org.springframework.transaction.support;

import io.micrometer.observation.Tags;
import org.junit.jupiter.api.Test;

import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;

import static org.assertj.core.api.BDDAssertions.then;

class DefaultTransactionTagsProviderTests {

	@Test
	void noTagsForContinuedTransaction() {
		TransactionStatus transactionStatus = new SimpleTransactionStatus(false);
		TransactionObservationContext context = new TransactionObservationContext(null, new TransactionManager() {

		});
		context.setTransactionStatus(transactionStatus);

		then(new DefaultTransactionTagsProvider().getLowCardinalityTags(context)).isSameAs(Tags.empty());
		then(new DefaultTransactionTagsProvider().getHighCardinalityTags(context)).isSameAs(Tags.empty());
	}

	@Test
	void tagsForNewTransaction() {
		TransactionStatus transactionStatus = new SimpleTransactionStatus(true);
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setName("foo");
		TransactionObservationContext context = new TransactionObservationContext(definition, new TransactionManager() {

		});
		context.setTransactionStatus(transactionStatus);

		then(new DefaultTransactionTagsProvider().getLowCardinalityTags(context)).isNotSameAs(Tags.empty());
		then(new DefaultTransactionTagsProvider().getHighCardinalityTags(context)).isNotSameAs(Tags.empty());
	}
}
