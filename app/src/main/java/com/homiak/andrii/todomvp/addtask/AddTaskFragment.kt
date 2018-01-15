package com.homiak.andrii.todomvp.addtask


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import com.homiak.andrii.todomvp.R
import com.homiak.andrii.todomvp.util.setupToolbar
import com.homiak.andrii.todomvp.util.toast
import kotlinx.android.synthetic.main.fragment_add_task.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTaskFragment: Fragment(), AddTaskContract.View {

    private lateinit var presenter: AddTaskContract.Presenter
    override fun setPresenter(presenter: AddTaskContract.Presenter) {
        this.presenter = presenter
    }

    override fun isActive(): Boolean = isAdded

    override fun showTitleError(err: String) {
        toast(err)
    }

    override fun showDescriptionError(err: String) {
        toast(err)
    }

    override fun showTaskSaved() {
        toast("Task saved!")
        activity.finish()
    }

    override fun showSaveError(err: String) {
        toast(err)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_add_task, container, false)
        (activity as? AppCompatActivity)?.setupToolbar(view.findViewById(R.id.toolbar))
        view.titleText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) = Unit

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.taskTitle = p0?.toString() ?: ""
            }
        })
        view.descText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) = Unit

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.taskDescription = p0?.toString() ?: ""
            }
        })

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.add_task, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.saveTask -> {
                presenter.handleSaveClick()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance(): AddTaskFragment = AddTaskFragment()
    }

}// Required empty public constructor