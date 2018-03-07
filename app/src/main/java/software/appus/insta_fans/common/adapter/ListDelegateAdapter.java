package software.appus.insta_fans.common.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ListDelegateAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private OnRecyclerItemClick<T> mListener;
    protected AdapterDelegatesManager<T> delegatesManager;
    private List<T> items;

    private ListDelegateAdapter() {
    }

    public ListDelegateAdapter(Builder<T> builder) {
        mListener = builder.getListener();
        delegatesManager = builder.getDelegatesManager();
        items = builder.getItems();
    }

    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType, mListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        delegatesManager.onBindViewHolder(items.get(position), position, holder, null);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items.get(position), position);
    }

    @Override
    public void onViewRecycled(BaseViewHolder<T> holder) {
        delegatesManager.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(BaseViewHolder<T> holder) {
        return delegatesManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder<T> holder) {
        delegatesManager.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(BaseViewHolder<T> holder) {
        delegatesManager.onViewDetachedFromWindow(holder);
    }

    /**
     * Get the items / data source of this adapter
     *
     * @return The items / data source
     */
    public List<T> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void updateItems(List<T> models) {
        items = (models == null ? new ArrayList<>() : models);
        notifyDataSetChanged();
    }

    public void updateItemByPosition(T model, int position) {
        updateItemByPosition(model, position, true);
    }

    public void updateItemByPosition(T model, int position, boolean notifyChanged) {
        if (getItems().size() >= position && position >= 0 && model != null) {
            items.set(position, model);
            if (notifyChanged) {
                notifyItemChanged(position);
            }
        }
    }

    public void updateItemsByPosition(SparseArray<T> array) {
        for (int i = 0; i < array.size(); i++) {
            int position = array.keyAt(i);
            updateItemByPosition(array.get(position), position);
        }
    }

    public List<T> getData() {
        return items;
    }

    public void removeItem(int position) {
        if (position >= 0 && position < items.size()) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeItem(int from, int count) {
        if (from >= 0 && from + count <= items.size()) {
            for (int i = 0; i < count; i++)
                items.remove(from);
            notifyItemRangeRemoved(from, count);
        }
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemRangeChanged(items.size() - 1, 1);
    }

    public void addItem(int position, T item) {
        if (position >= 0 && position < items.size()) {
            items.add(position, item);
            notifyItemInserted(position);
        }
    }

    public void addItem(int from, List<T> models) {
        if (from >= 0 && from < items.size() && models != null) {
            //   for (int i = from; i <= models.size(); i++)
            items.addAll(from, models);
            notifyItemRangeInserted(from, models.size());
        }
    }

    public T getItem(int position) {
        return (position >= 0 && position < getItemCount()) ? items.get(position) : null;
    }

    public void insertItems(@NonNull List<T> data, int position) {
        if (position >= 0 && items.size() >= position && !data.isEmpty()) {
            items.addAll(position, data);
            notifyItemRangeInserted(position, data.size());
        }
    }

    public void deleteItems(int position, final int count) {
        int lCount = count;
        if (position >= 0 && position < items.size() && (position + lCount) <= items.size()) {
            while (lCount > 0) {
                items.remove(position);
                lCount--;
            }
            notifyItemRangeRemoved(position, count);
        }
    }

    public static class Builder<T> {
        private OnRecyclerItemClick<T> mListener;
        private AdapterDelegatesManager<T> delegatesManager;
        private List<T> items;


        public Builder() {
            delegatesManager = new AdapterDelegatesManager<>();
            items = new ArrayList<>();
        }

        public OnRecyclerItemClick<T> getListener() {
            return mListener;
        }

        public Builder<T> setListener(OnRecyclerItemClick<T> listener) {
            this.mListener = listener;
            return this;
        }

        public AdapterDelegatesManager<T> getDelegatesManager() {
            return delegatesManager;
        }

        public Builder<T> setDelegatesManager(AdapterDelegatesManager<T> delegatesManager) {
            this.delegatesManager = delegatesManager;
            return this;
        }

        public List<T> getItems() {
            return items;
        }

        public Builder<T> setItems(List<T> items) {
            this.items = items;
            return this;
        }

        public Builder<T> addDelegate(AdapterDelegate<T> item) {
            if (delegatesManager == null) {
                throw new IllegalArgumentException("provide DelegateManager");
            }
            if (items == null) {
                throw new IllegalArgumentException("provide AdapterDelegate<T> item");
            }
            delegatesManager.addDelegate(item);
            return this;
        }

        public ListDelegateAdapter<T> build() {
            return new ListDelegateAdapter<>(this);
        }

    }


}