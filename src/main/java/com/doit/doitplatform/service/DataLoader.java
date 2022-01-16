package com.doit.doitplatform.service;

import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.PageStyle;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.model.QuotationCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryService categoryService;
    private final QuotationService quotationService;
    private final QuotationCategoryService quotationCategoryService;
    private final PageStyleService pageStyleService;
    private final String BASE_URL;

    @Autowired
    public DataLoader(@Value("${application.base.url}") String BASE_URL, CategoryService categoryService, QuotationService quotationService, QuotationCategoryService quotationCategoryService, PageStyleService pageStyleService) {
        this.categoryService = categoryService;
        this.quotationService = quotationService;
        this.quotationCategoryService = quotationCategoryService;
        this.pageStyleService = pageStyleService;
        this.BASE_URL = BASE_URL;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryService.isTableEmpty()) {
            Category general = new Category();
            general.setIsGeneral(true);
            general.setTopic("General");
            categoryService.save(general);

            Category hardTimes = new Category();
            hardTimes.setIsGeneral(true);
            hardTimes.setTopic("Hard times");
            categoryService.save(hardTimes);

            Quotation q1 = new Quotation();
            q1.setQuote("My Filling are valid");

            Quotation q2 = new Quotation();
            q2.setQuote("I Welcome love into my life");

            Quotation q3 = new Quotation();
            q3.setQuote("I am allowed to say no to others and yes to myself");

            Quotation q4 = new Quotation();
            q4.setQuote("I deserve a peaceful and loving life");

            Quotation q5 = new Quotation();
            q5.setQuote("I am attracting deep love");

            Quotation q6 = new Quotation();
            q6.setQuote("I don't need to be perfect to be sexy");

            Quotation q7 = new Quotation();
            q7.setQuote("I will overcome this situation");

            Quotation q8 = new Quotation();
            q8.setQuote("I have all I need");

            Quotation q9 = new Quotation();
            q9.setQuote("Life is beautiful");

            Quotation q10 = new Quotation();
            q10.setQuote("I am magical");

            quotationService.save(q1);
            quotationService.save(q2);
            quotationService.save(q3);
            quotationService.save(q4);
            quotationService.save(q5);
            quotationService.save(q6);
            quotationService.save(q7);
            quotationService.save(q8);
            quotationService.save(q9);
            quotationService.save(q10);

            QuotationCategory quotationCategory1 = new QuotationCategory();
            quotationCategory1.setCategory(general);
            quotationCategory1.setQuotation(q1);
            quotationCategoryService.save(quotationCategory1);

            QuotationCategory quotationCategory2 = new QuotationCategory();
            quotationCategory2.setCategory(general);
            quotationCategory2.setQuotation(q2);
            quotationCategoryService.save(quotationCategory2);

            QuotationCategory quotationCategory3 = new QuotationCategory();
            quotationCategory3.setCategory(general);
            quotationCategory3.setQuotation(q3);
            quotationCategoryService.save(quotationCategory3);

            QuotationCategory quotationCategory4 = new QuotationCategory();
            quotationCategory4.setCategory(general);
            quotationCategory4.setQuotation(q4);
            quotationCategoryService.save(quotationCategory4);

            QuotationCategory quotationCategory5 = new QuotationCategory();
            quotationCategory5.setCategory(general);
            quotationCategory5.setQuotation(q5);
            quotationCategoryService.save(quotationCategory5);

            System.out.println("in data loader");

            QuotationCategory quotationCategory6 = new QuotationCategory();
            quotationCategory6.setCategory(general);
            quotationCategory6.setQuotation(q6);
            quotationCategoryService.save(quotationCategory6);


            QuotationCategory quotationCategory7 = new QuotationCategory();
            quotationCategory7.setCategory(hardTimes);
            quotationCategory7.setQuotation(q6);
            quotationCategoryService.save(quotationCategory7);

            QuotationCategory quotationCategory8 = new QuotationCategory();
            quotationCategory8.setCategory(hardTimes);
            quotationCategory8.setQuotation(q7);
            quotationCategoryService.save(quotationCategory8);

            QuotationCategory quotationCategory9 = new QuotationCategory();
            quotationCategory9.setCategory(hardTimes);
            quotationCategory9.setQuotation(q8);
            quotationCategoryService.save(quotationCategory9);

            QuotationCategory quotationCategory10 = new QuotationCategory();
            quotationCategory10.setCategory(hardTimes);
            quotationCategory10.setQuotation(q9);
            quotationCategoryService.save(quotationCategory10);

            QuotationCategory quotationCategory11 = new QuotationCategory();
            quotationCategory11.setCategory(hardTimes);
            quotationCategory11.setQuotation(q10);
            quotationCategoryService.save(quotationCategory11);


            PageStyle style = new PageStyle();
            style.setBackgroundImage(BASE_URL + "/image/image1.jpg");
            style.setFontColor("#EC9F3B");


            PageStyle style2 = new PageStyle();
            style2.setBackgroundImage(BASE_URL + "/image/image2.jpg");
            style2.setFontColor("#EC9F3B");

            PageStyle style3 = new PageStyle();
            style3.setBackgroundImage(BASE_URL + "/image/image3.jpg");
            style3.setFontColor("#EC9F3B");

            PageStyle style4 = new PageStyle();
            style4.setBackgroundImage(BASE_URL + "/image/image4.jpg");
            style4.setFontColor("#EC9F3B");


            PageStyle styl5 = new PageStyle();
            styl5.setBackgroundImage(BASE_URL + "/image/image5.jpg");
            styl5.setFontColor("#EC9F3B");


            PageStyle styl6 = new PageStyle();
            styl6.setBackgroundImage(BASE_URL + "/image/image6.jpg");
            styl6.setFontColor("#EC9F3B");

            PageStyle styl7 = new PageStyle();
            styl7.setBackgroundImage(BASE_URL + "/image/image7.jpg");
            styl7.setFontColor("#EC9F3B");


            PageStyle styl8 = new PageStyle();
            styl8.setBackgroundImage(BASE_URL + "/image/image8.jpg");
            styl8.setFontColor("#EC9F3B");

            PageStyle styl9 = new PageStyle();
            styl9.setBackgroundImage(BASE_URL + "/image/image9.jpg");
            styl9.setFontColor("#EC9F3B");


            PageStyle styl10 = new PageStyle();
            styl10.setBackgroundImage(BASE_URL + "/image/image10.jpg");
            styl10.setFontColor("#EC9F3B");

            PageStyle style11 = new PageStyle();
            style11.setBackgroundImage(BASE_URL + "/image/image11.jpg");
            style11.setFontColor("#EC9F3B");

            PageStyle styl12 = new PageStyle();
            styl12.setBackgroundImage(BASE_URL + "/image/image12.jpg");
            styl12.setFontColor("#EC9F3B");

            PageStyle styl13 = new PageStyle();
            styl13.setBackgroundImage(BASE_URL + "/image/image13.jpg");
            styl13.setFontColor("#EC9F3B");

            PageStyle styl14 = new PageStyle();
            styl14.setBackgroundImage(BASE_URL + "/image/image14.jpg");
            styl14.setFontColor("#EC9F3B");

            PageStyle styl15 = new PageStyle();
            styl15.setBackgroundImage(BASE_URL + "/image/image15.jpg");
            styl15.setFontColor("#EC9F3B");

            pageStyleService.save(style);
            pageStyleService.save(style2);
            pageStyleService.save(style3);
            pageStyleService.save(style4);
            pageStyleService.save(styl5);
            pageStyleService.save(styl6);
            pageStyleService.save(styl7);
            pageStyleService.save(styl8);
            pageStyleService.save(styl9);
            pageStyleService.save(styl10);
            pageStyleService.save(style11);
            pageStyleService.save(styl12);
            pageStyleService.save(styl13);
            pageStyleService.save(styl14);
            pageStyleService.save(styl15);


        }

    }

}
