package com.bemore.southerntireservice.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class RecyclerViewArrayAdapter<T, VH extends RecyclerViewHolder>
        extends RecyclerView.Adapter<VH> {

    private List<T> mItems;
    protected RecyclerViewHolder.OnItemClickListener onItemClickListener;

    public RecyclerViewArrayAdapter() {
        super();
    }

    public RecyclerViewArrayAdapter(List<T> items) {
        this();
        bindData(items);
    }

    public RecyclerViewArrayAdapter(List<T> items, RecyclerViewHolder.OnItemClickListener onItemClickListener) {
        this(items);
        this.onItemClickListener = onItemClickListener;
    }

    public void bindData(final List<T> items)
    {
        this.mItems = items;
        this.notifyDataSetChanged();
    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    public void add(final T object) {
        mItems.add(object);
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * Remove all elements from the list.
     */
    public void clear() {
        final int size = getItemCount();
        mItems.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public T getItem(final int position) {
        return mItems.get(position);
    }

    public long getItemId(final int position) {
        return position;
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item to retrieve the position of.
     * @return The position of the specified item.
     */
    public int getPosition(final T item) {
        return mItems.indexOf(item);
    }

    /**
     * Inserts the specified object at the specified index in the array.
     *
     * @param object The object to insert into the array.
     * @param index  The index at which the object must be inserted.
     */
    public void insert(final T object, int index) {
        mItems.add(index, object);
        notifyItemInserted(index);

    }

    /**
     * Removes the specified object from the array.
     *
     * @param object The object to remove.
     */
    public void remove(T object) {
        final int position = getPosition(object);
        mItems.remove(object);
        notifyItemRemoved(position);
    }

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(), parent, false);
        return onCreateViewHolder(view);
    }

    public abstract VH onCreateViewHolder(View view);

    public abstract int getLayoutResId();

    @Override
    public final void onBindViewHolder(final VH holder, final int position)
    {
        final T item = this.getItem(position);
        this.onBindViewHolder(holder, item);
    }

    public abstract void onBindViewHolder(final VH holder, final T item);

    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained in this adapter.
     */
    public void sort(Comparator<? super T> comparator) {
        Collections.sort(mItems, comparator);
        notifyItemRangeChanged(0, getItemCount());
    }
}