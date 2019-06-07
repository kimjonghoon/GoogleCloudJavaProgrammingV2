/**
 * Copyright 2014-2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.guestbook;

import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextEvent;

import net.java_school.blog.Article;
import net.java_school.blog.Category;
import net.java_school.blog.Lang;

import net.java_school.user.GaeUser;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
@WebListener
public class OfyHelper implements ServletContextListener {
	public static void register() {
		ObjectifyService.register(Guestbook.class);
		ObjectifyService.register(Greeting.class);
		ObjectifyService.register(Lang.class);
		ObjectifyService.register(Category.class);
		ObjectifyService.register(Article.class);
		ObjectifyService.register(GaeUser.class);
	}

	public void contextInitialized(ServletContextEvent event) {
		// This will be invoked as part of a warmup request, or the first user
		// request if no warmup request was invoked.
		register();
	}

	public void contextDestroyed(ServletContextEvent event) {
		// App Engine does not currently invoke this method.
	}

}