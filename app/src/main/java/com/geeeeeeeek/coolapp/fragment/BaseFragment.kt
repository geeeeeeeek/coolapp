package com.geeeeeeeek.coolapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
    /**
     * 当前页数
     */
    var CURRENT_PAGE = 1
    /**
     * 每页容量- 每页有多少条记录
     */
    var TOTAL_PAGE = 0
    var pView:View? = null

    protected abstract fun getContentViewLayoutId(): Int

    protected abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pView = inflater.inflate(getContentViewLayoutId(), container, false)
        return pView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
    }

    override fun onDestroy(){
        super.onDestroy()
    }




}

