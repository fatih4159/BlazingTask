package de.agx.blazingtask.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


    /**
     * A generic ViewHolder that wraps a generated ViewDataBinding class.
     *
     * @param <T> The type of the ViewDataBinding class
     */
    public class DataBoundViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
        public final T binding;

        public DataBoundViewHolder(T binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Creates a new ViewHolder for the given layout file.
         * <p>
         * The provided layout must be using data binding.
         *
         * @param parent   The RecyclerView
         * @param layoutId The layout id that should be inflated. Must use data binding
         * @param <T>      The type of the Binding class that will be generated for the <code>layoutId</code>.
         * @return A new ViewHolder that has a reference to the binding class
         */
        public static <T extends ViewDataBinding> DataBoundViewHolder<T> create(ViewGroup parent,
                                                                                @LayoutRes int layoutId) {
            T binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    layoutId, parent, false);
            return new DataBoundViewHolder<T>(binding);
        }
    }

