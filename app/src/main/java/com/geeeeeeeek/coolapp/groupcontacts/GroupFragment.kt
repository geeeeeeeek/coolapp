package com.geeeeeeeek.coolapp.groupcontacts

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.geeeeeeeek.coolapp.R
import com.geeeeeeeek.coolapp.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_group_contacts.*
import java.util.*

/**
 * Created by XiaoQingsong
 * Date: 2020/4/3
 * Time: 10:05 AM
 */
class GroupFragment : BaseFragment() {

    private val adapter: NodeTreeAdapter = NodeTreeAdapter()

    override fun getContentViewLayoutId(): Int {
        return R.layout.fragment_group_contacts
    }

    override fun initialize() {
        rv_list!!.layoutManager = LinearLayoutManager(activity)
        rv_list!!.adapter = adapter

        adapter.setList(getEntity())
    }

    // 获取数据
    private fun getEntity(): List<BaseNode>? {
        val list: MutableList<BaseNode> = ArrayList()
        for (i in 0..7) {
            val secondNodeList: MutableList<BaseNode> = ArrayList()
            for (n in 0..5) {
                val seNode = SecondNode(null, "Second Node $n")
                secondNodeList.add(seNode)
            }
            val entity = FirstNode(secondNodeList, "First Node $i")
            list.add(entity)
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance(): GroupFragment {
            val args = Bundle()
            val fragment = GroupFragment()
            fragment.arguments = args
            return fragment
        }

    }
}