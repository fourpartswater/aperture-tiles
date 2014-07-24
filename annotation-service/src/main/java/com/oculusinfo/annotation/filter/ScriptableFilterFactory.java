/*
 * Copyright (c) 2014 Oculus Info Inc.
 * http://www.oculusinfo.com/
 *
 * Released under the MIT License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oculusinfo.annotation.filter;

import com.oculusinfo.annotation.filter.impl.NMostRecentByGroupFilter;
import com.oculusinfo.annotation.filter.impl.ScriptableFilter;
import com.oculusinfo.factory.ConfigurableFactory;
import com.oculusinfo.factory.properties.JSONProperty;
import com.oculusinfo.factory.properties.StringProperty;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ScriptableFilterFactory extends ConfigurableFactory<AnnotationFilter> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScriptableFilterFactory.class);

	
	public static StringProperty SCRIPT_STRING = new StringProperty("script",
			"The javascript script string that evaluates to a boolean based off a single annotation",
			null);

	public ScriptableFilterFactory(String factoryName, ConfigurableFactory<?> parent, List<String> path) {
		super(factoryName, AnnotationFilter.class, parent, path);
		
		addProperty(SCRIPT_STRING);
	}

	@Override
	protected AnnotationFilter create() {
		try {
			String script = getPropertyValue(SCRIPT_STRING);
			return new ScriptableFilter(script);
		}
		catch (Exception e) {
			LOGGER.error("Error trying to create ScriptableFilter", e);
		}
		return null;
	}
}