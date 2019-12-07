package com.example.footballmatchschedule.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.footballmatchschedule.Model.Item
import com.example.footballmatchschedule.R
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk27.coroutines.onClick

class RecyclerViewAdapter(
    private val context: Context,
    public val items: List<Item>,
    val clickListener: (Item) -> Unit
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(
            list_club(
                items
            ).createView(AnkoContext.create(parent.context, parent))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], clickListener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private val name = containerView.findViewById<TextView>(R.id.name)
        private val image = containerView.findViewById<ImageView>(R.id.gambar)


        fun bindItem(items: Item, listener: (Item) -> Unit) {
            name.text = items.name
            items.image?.let { Picasso.get().load(it).fit().into(image) }
            //itemView.name.text = items.name
            //items.image?.let { Picasso.get().load(it).fit().into(image)
            itemView.setOnClickListener {
                listener(items)
                //startActivity<DetailLiga>("name" to "dicoding")

            }

        }
    }

    class list_club(items: List<Item>) : AnkoComponent<ViewGroup> {
        var size = items.get(0).name
        //private var list: ArrayList<Item> = arrayListOf()
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                cardView() {
                    onClick {
                        toast("Berhasil Pindah Activity")
                        //val intent = Intent(context, DetailLiga::class.java)
                        //intent.putExtra("name", "Dicoding")
                        //intent.setFlag(Intent.FLAG_ACTIVITY_SINGLE_TOP)

                    }
                    lparams {
                        width = wrapContent
                        height = wrapContent
                        topMargin = dip(4)
                        bottomMargin = dip(4)
                        leftMargin = dip(8)
                        rightMargin = dip(8)
                        gravity = Gravity.CENTER

                    }

                    relativeLayout() {
                        lparams(width = matchParent, height = wrapContent)
                        imageView() {
                            id = R.id.gambar
                        }.lparams {
                            width = dip(150)
                            height = dip(220)
                            bottomMargin = dip(4)
                        }
                        textView() {
                            id = R.id.name
                            textSize = 15f

                        }.lparams {
                            bottomOf(R.id.gambar)
                        }
                    }

                }
            }
        }

    }
}