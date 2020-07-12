package net.java_school.controller;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.objectify.Key;

import net.java_school.blog.Article;
import net.java_school.blog.Category;
import net.java_school.blog.Lang;

@Controller
@RequestMapping("blog")
public class BlogController {

	private String getChapter(String category) {
		String chapter = null;

		switch (category) {
			case "java":
				chapter = "Java";
				break;
			case "jdbc":
				chapter = "JDBC";
				break;
			case "jsp":
				chapter = "JSP";
				break;
			case "css-layout":
				chapter = "CSS Layout";
				break;
			case "jsp-pjt":
				chapter = "JSP Project";
				break;
			case "spring":
				chapter = "Spring";
				break;
			case "javascript":
				chapter = "JavaScript";
				break;
			case "google-app-engine":
				chapter = "Google Cloud";
				break;	
			case "blog":
				chapter = "Blog";
				break;
		}

		return chapter;
	}
	
	@GetMapping
	public String index(Model model, Locale locale) {
		String lang = locale.getLanguage();
		Key<Lang> theLang = Key.create(Lang.class, lang);
		Key<Category> theCategory = Key.create(theLang, Category.class, "blog");

		List<Article> articles = ofy()
			.load()
			.type(Article.class)
			.ancestor(theLang)
			.ancestor(theCategory)
			.order("-date")
			.list();

		model.addAttribute("articles", articles);

		return "blog";
	}

	@GetMapping("{id}")
	public String blog(@PathVariable("id") String id, Locale locale, Model model) {
		String lang = locale.getLanguage();
		Key<Lang> theLang = Key.create(Lang.class, lang);
		Key<Category> theCategory = Key.create(theLang, Category.class, "blog");
		Key<Article> theArticle = Key.create(theCategory, Article.class, id);
		Article article = ofy().load().key(theArticle).now();
		if (article == null) return "redirect:/blog";
		model.addAttribute("article", article);

		return "blog/" + id;
	}

}
