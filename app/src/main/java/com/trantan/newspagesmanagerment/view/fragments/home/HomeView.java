package com.trantan.newspagesmanagerment.view.fragments.home;

import com.trantan.newspagesmanagerment.model.response.Category;
import com.trantan.newspagesmanagerment.model.response.Post;

import java.util.List;

public interface HomeView {
    void refreshListCategories(List<Category> categories);
}
