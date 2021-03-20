package `in`.codingstudio.to_dolist

import `in`.codingstudio.calendardayevent.ToDo
import `in`.codingstudio.to_dolist.databinding.DayViewCalendarFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.*

class FragmentCalendarDayView : Fragment() {

    lateinit var binding: DayViewCalendarFragmentBinding
    private var todos = ArrayList<ToDo>()
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DayViewCalendarFragmentBinding.inflate(layoutInflater)

        works()

        return binding.root
    }

    private fun works() {

        day = arguments?.getInt("day")!!
        month = arguments?.getInt("month")!!
        year = arguments?.getInt("year")!!

        if (day > 15){
            binding.calendarDayEvent.setLimitTime(0, 25)

            val startTime = Calendar.getInstance()
            startTime.set(Calendar.HOUR_OF_DAY, 15)
            startTime.set(Calendar.MINUTE,0)

            val endTime = Calendar.getInstance()
            endTime.set(Calendar.HOUR_OF_DAY, 18)
            endTime.set(Calendar.MINUTE,0)

            val todo = ToDo()
            todo.topic = "meeting"
            todo.color = resources.getColor(R.color.orange)
            todo.description = "have to call someone"
            todo.startTime = startTime
            todo.endTime = endTime

            todos.add(todo)

            binding.calendarDayEvent.setTodos(todos)

        }else{
            binding.calendarDayEvent.setLimitTime(0, 25)

            val startTime = Calendar.getInstance()
            startTime.set(Calendar.HOUR_OF_DAY, 15)
            startTime.set(Calendar.MINUTE,0)

            val endTime = Calendar.getInstance()
            endTime.set(Calendar.HOUR_OF_DAY, 18)
            endTime.set(Calendar.MINUTE,0)

            val todo = ToDo()
            todo.topic = "inbox"
            todo.color = resources.getColor(R.color.baby_blue)
            todo.description = "Finish android studio project Finish android studio project"
            todo.startTime = startTime
            todo.endTime = endTime

            todos.add(todo)

            binding.calendarDayEvent.setTodos(todos)
        }




    }

}