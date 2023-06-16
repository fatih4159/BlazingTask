package de.agx.blazingtask.ui.adapter;


import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import de.agx.blazingtask.R;
import de.agx.blazingtask.databinding.TasktimeItemBinding;
import de.agx.blazingtask.ui.types.TaskTime;

import java.util.ArrayList;
import java.util.List;

public  class AdapterTaskTime extends DataBoundAdapter<TasktimeItemBinding> {
        List<TaskTime> mDeliveriesList = new ArrayList<>();

        public AdapterTaskTime(List<TaskTime> tasks) {
            super(R.layout.tasktime_item);
            //Arrays.sort(tasks, new ReverseDelivComparator());
            //Collections.addAll(mDeliveriesList, tasks);
            mDeliveriesList.clear();
            mDeliveriesList.addAll( tasks );
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            //LogMsg(9,"AdapterDelivery getItemCount["+mDeliveriesList.size()+"]");
            return mDeliveriesList.size();
        }
        CompoundButton lastchecked =null;
        int lcPosition =0;


        @Override
        protected void bindItem(DataBoundViewHolder<TasktimeItemBinding> holder, int position, List<Object> payloads) {
            //LogMsg(9,"AdapterDelivery bindItem["+position+"] of["+mDeliveriesList.size()+"] List["+payloads.size()+"]");
            holder.binding.setData(mDeliveriesList.get(position));

        }
    }
