package com.geeeeeeeek.coolapp.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeeeeeeek.coolapp.R
import com.geeeeeeeek.coolapp.adapter.ContactAdapter
import com.geeeeeeeek.coolapp.model.ContactBean
import com.geeeeeeeek.coolapp.utils.HeaderRecyclerAndFooterWrapperAdapter
import com.geeeeeeeek.coolapp.utils.ViewHolder
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration
import kotlinx.android.synthetic.main.fragment_contacts.*

/**
 * Created by XiaoQingsong
 * Date: 2020/4/3
 * Time: 10:05 AM
 */
class ContactsFragment : BaseFragment() {

    private var mAdapter: ContactAdapter? = null
    private var mHeaderAdapter: HeaderRecyclerAndFooterWrapperAdapter? = null
    private var mManager: LinearLayoutManager? = null
    private var mDatas: ArrayList<ContactBean>? = null
    private var mDecoration: SuspensionDecoration? = null

    override fun getContentViewLayoutId(): Int {
        return R.layout.fragment_contacts
    }

    override fun initialize() {
        rv.layoutManager = LinearLayoutManager(activity).also { mManager = it }

        mAdapter = ContactAdapter(activity, mDatas)
        mHeaderAdapter = object : HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            override fun onBindHeaderHolder(holder: ViewHolder, headerPos: Int, layoutId: Int, o: Any?) {
                holder.setText(R.id.tvCity, o as String?)
            }
        }
        mHeaderAdapter!!.setHeaderView(R.layout.item_city, "测试头部")

        rv.adapter = mHeaderAdapter
        rv.addItemDecoration(SuspensionDecoration(activity, mDatas).setHeaderViewCount(mHeaderAdapter!!.headerViewCount).also { mDecoration = it })

        //如果add两个，那么按照先后顺序，依次渲染。
        rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        indexBar.setmPressedShowTextView(tvSideBarHint) //设置HintTextView
                .setNeedRealIndex(true) //设置需要真实的索引
                .setmLayoutManager(mManager) //设置RecyclerView的LayoutManager

        initDatas(resources.getStringArray(R.array.provinces))
    }

    private fun initDatas(data: Array<String>) {
        mDatas = ArrayList()
        for (i in data.indices) {
            val cityBean = ContactBean()
            cityBean.city = data[i]
            mDatas!!.add(cityBean)
        }
        indexBar.setmSourceDatas(mDatas)
                .setHeaderViewCount(mHeaderAdapter!!.headerViewCount) //设置HeaderView数量
                .invalidate()
        mAdapter!!.datas = mDatas
        mHeaderAdapter!!.notifyDataSetChanged()
        mDecoration!!.setmDatas(mDatas)
    }

    fun updateDatas(view: View?) {
        for (i in 0..4) {
            mDatas!!.add(ContactBean("东京"))
            mDatas!!.add(ContactBean("大阪"))
        }
        indexBar.setmSourceDatas(mDatas)
                .invalidate()
        mHeaderAdapter!!.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(): ContactsFragment {
            val args = Bundle()
            val fragment = ContactsFragment()
            fragment.arguments = args
            return fragment
        }

    }
}