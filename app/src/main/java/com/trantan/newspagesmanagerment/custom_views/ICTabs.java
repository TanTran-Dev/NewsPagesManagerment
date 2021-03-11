package com.trantan.newspagesmanagerment.custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.trantan.newspagesmanagerment.Extensions.TextExtension;
import com.trantan.newspagesmanagerment.Extensions.ViewExtension;
import com.trantan.newspagesmanagerment.R;
import com.trantan.newspagesmanagerment.databinding.TabsCustomBinding;

import java.util.ArrayList;
import java.util.List;

public class ICTabs extends FrameLayout implements View.OnClickListener {

    private Context context;

    private List<IC_TAB> ic_tabs = new ArrayList<>();
    private static final int lineNormal = R.color.line_tab_normal;
    private static final int lineSelected = R.color.line_tab_selected;

    private static final int textSelected = R.color.text_tab_selected;
    private static final int textNormal = R.color.text_tab_normal;

    private ICTabSelectedListener listener;

    public void setListener(ICTabSelectedListener listener) {
        this.listener = listener;
    }

    public ICTabs(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ICTabs(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ICTabs(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        final TabsCustomBinding binding = DataBindingUtil.inflate
                (
                        LayoutInflater.from(context),
                        R.layout.tabs_custom,
                        this,
                        true
                );
        onViewCreated(binding);
        setItemListener(binding);
    }

    private void onViewCreated(TabsCustomBinding binding) {
        final IC_TAB tab1 = new IC_TAB(binding.lineTab1, binding.btnTab1, binding.tvTitleTab1,0);
        final IC_TAB tab2 = new IC_TAB(binding.lineTab2, binding.btnTab2, binding.tvTitleTab2,1);
        final IC_TAB tab3 = new IC_TAB(binding.lineTab3, binding.btnTab3, binding.tvTitleTab3,2);
        final IC_TAB tab4 = new IC_TAB(binding.lineTab4, binding.btnTab4, binding.tvTitleTab4,3);
        final IC_TAB tab5 = new IC_TAB(binding.lineTab5, binding.btnTab5, binding.tvTitleTab5,4);
        ic_tabs.add(tab1);
        ic_tabs.add(tab2);
        ic_tabs.add(tab3);
        ic_tabs.add(tab4);
        ic_tabs.add(tab5);
    }

    private void setItemListener(TabsCustomBinding binding) {
        binding.btnTab1.setOnClickListener(this);
        binding.btnTab2.setOnClickListener(this);
        binding.btnTab3.setOnClickListener(this);
        binding.btnTab4.setOnClickListener(this);
        binding.btnTab5.setOnClickListener(this);
    }

    private void handleTabSelected(int id) {
        for (IC_TAB tab_ic : ic_tabs) {
            final boolean is_selected = id == tab_ic.getBtn().getId();
            final int colorLine = is_selected ? lineSelected : lineNormal;
            final int colorText = is_selected ? textSelected : textNormal;
            ViewExtension.changeColor(context, tab_ic.getLine(), colorLine);
            TextExtension.changeColor(context, tab_ic.getTextView(), colorText);
        }
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        handleTabSelected(id);

        if (listener != null) {
            switch (id) {
                case R.id.btnTab1:
                    listener.ICTabListener(TAB_IC.tab1,0);
                    break;
                case R.id.btnTab2:
                    listener.ICTabListener(TAB_IC.tab2,1);
                    break;
                case R.id.btnTab3:
                    listener.ICTabListener(TAB_IC.tab3,2);
                    break;
                case R.id.btnTab4:
                    listener.ICTabListener(TAB_IC.tab4,3);
                    break;
                case R.id.btnTab5:
                    listener.ICTabListener(TAB_IC.tab5,4);
                    break;
            }
        }
    }

    private class IC_TAB {
        private View line;
        private LinearLayout btn;
        private TextView textView;
        private int position;

        public IC_TAB(View line, LinearLayout btn, TextView textView, int position) {
            this.line = line;
            this.btn = btn;
            this.textView = textView;
            this.position = position;
        }

        public View getLine() {
            return line;
        }

        public LinearLayout getBtn() {
            return btn;
        }

        public TextView getTextView() {
            return textView;
        }

        public int getPosition() {
            return position;
        }
    }

    public interface ICTabSelectedListener {
        void ICTabListener(TAB_IC tab_ic, int position);
    }

    public enum TAB_IC {
        tab1, tab2, tab3, tab4, tab5
    }
}
