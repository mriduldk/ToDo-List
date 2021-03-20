package `in`.codingstudio.to_dolist

import `in`.codingstudio.calendardayevent.ToDo
import `in`.codingstudio.to_dolist.databinding.ActivityCalendarBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

@Suppress("UNREACHABLE_CODE")
class CalendarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalendarBinding
    private val today : Calendar = Calendar.getInstance()
    private val adapter = CalendarViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTodos(today.time)

        horizontalRowCalendar();

    }

    private fun horizontalRowCalendar() {

        today.time = Date()

        val myCalendarViewManager = object :
            CalendarViewManager {
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                // set date to calendar according to position where we are
                val cal = Calendar.getInstance()
                cal.time = date

                return if (today[Calendar.YEAR] == cal[Calendar.YEAR] && today[Calendar.MONTH] == cal[Calendar.MONTH] && today[Calendar.DAY_OF_MONTH] == cal[Calendar.DAY_OF_MONTH] ){
                    R.layout.today_date_layout
                }else{
                    if (isSelected){

                        setUpTodos(date)

                        R.layout.todo_select_day
                    } else{
                        R.layout.deselect_day
                    }
                }
            }

            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {

                val numDate : TextView = holder.itemView.findViewById(R.id.tv_date_calendar_item)
                val day : TextView = holder.itemView.findViewById(R.id.tv_day_calendar_item)
                val month : TextView = holder.itemView.findViewById(R.id.tv_month_calendar_item)

                numDate.text = DateUtils.getDayNumber(date)
                day.text = DateUtils.getDay3LettersName(date)
                month.text = DateUtils.getMonth3LettersName(date)
            }
        }


        val mySelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                return true
                }
            }


        val singleRowCalendar = binding.mainSingleRowCalendar.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            pastDaysCount = GlobalValues.PREVIOUS_DAY_COUNT
            futureDaysCount = GlobalValues.FUTURE_DAY_COUNT
            includeCurrentDate = true
            init()
            scrollToPosition(GlobalValues.PREVIOUS_DAY_COUNT)
        }

    }


    fun setUpTodos(date : Date){

        // Todo Extract todo from database and paste it to the view
        // TODO if possible implement viewpager with left and right swipe
        binding.calendarDayEvent.refresh()

        binding.calendarDayEvent.setLimitTime(0, 25)

        val calTodo : Calendar = Calendar.getInstance()
        calTodo.time = date

        if (calTodo[Calendar.DAY_OF_MONTH] > 15){

            val todos = ArrayList<ToDo>()

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
        }else{

            val todos = ArrayList<ToDo>()

            val startTime = Calendar.getInstance()
            startTime.set(Calendar.HOUR_OF_DAY, 15)
            startTime.set(Calendar.MINUTE,0)

            val endTime = Calendar.getInstance()
            endTime.set(Calendar.HOUR_OF_DAY, 17)
            endTime.set(Calendar.MINUTE,0)

            val todo = ToDo()
            todo.topic = "meeting"
            todo.color = resources.getColor(R.color.orange)
            todo.description = "Call person"
            todo.startTime = startTime
            todo.endTime = endTime

            todos.add(todo)

            binding.calendarDayEvent.setTodos(todos)
        }
    }



    private val myCalendarChangesObserver = object : CalendarChangesObserver {
        override fun whenWeekMonthYearChanged(weekNumber: String,monthNumber: String,monthName: String,year: String,date: Date) {
            super.whenWeekMonthYearChanged(weekNumber, monthNumber, monthName, year, date)
        }

        override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
            super.whenSelectionChanged(isSelected, position, date)
        }

        override fun whenCalendarScrolled(dx: Int, dy: Int) {
            super.whenCalendarScrolled(dx, dy)
        }

        override fun whenSelectionRestored() {
            super.whenSelectionRestored()
        }

        override fun whenSelectionRefreshed() {
            super.whenSelectionRefreshed()
        }





    }

}


