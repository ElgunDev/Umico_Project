package com.matrix.android105_android.presentation.ui.Home

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.Repository.Home.Shops.Shop
import com.matrix.android105_android.data.Repository.Products.Product
import com.matrix.android105_android.databinding.ItemImageButtonBinding
import com.matrix.android105_android.databinding.ItemProductsBinding
import java.util.concurrent.TimeUnit

class ProductAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewTypeProduct = 1
    private val viewTypeImageButton = 2
    private val additionalImageButtonCount = 1
    private var dataLoaded = false

    private val diffCallBack = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun getItemViewType(position: Int): Int {
        return if (position<diffUtil.currentList.size){
              viewTypeProduct
        }
        else if (dataLoaded){
            viewTypeImageButton
        }
        else{
            viewTypeProduct
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == viewTypeProduct) {
            val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProductViewHolder(binding)
        }
        else{
            val binding = ItemImageButtonBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
            return ImageButtonViewHolder(binding)

        }
    }

    override fun getItemCount(): Int {
        return if (dataLoaded) {
            diffUtil.currentList.size + additionalImageButtonCount
        } else {
            diffUtil.currentList.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == viewTypeProduct){
        val product = diffUtil.currentList[position]
            (holder as ProductViewHolder).bind(product.image , product.credit,product.discountPrice, product.discountRate,product.name , product.price, product.rating)
         }
        else{
            (holder as ImageButtonViewHolder).bind()
        }

    }


    inner class ProductViewHolder(private val binding:ItemProductsBinding):RecyclerView.ViewHolder(binding.root){
         fun bind(imageUrl:String,credit:String,discountPrize:String , discountRate:String , name:String,price:String,rating:String ){
             binding.txtCreditMonth.text = credit
             binding.discountedPrice.text = discountPrize
             binding.discountRate.text = discountRate
             binding.txtNameProduct.text = name
             binding.price.text = price
             binding.ratingBar.rating = rating.toFloat()
             Glide.with(binding.root.context)
                 .load(imageUrl)
                 .into(binding.imgProduct)

             lateinit var countDownTimer: CountDownTimer
              val totalTimer = (23*60*60*1000) + (59*60*1000) + (59*1000)
             countDownTimer = object :CountDownTimer(totalTimer.toLong() , 1000L){
                 override fun onTick(millisUntilFinished: Long) {
                     val hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                     val minute = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)%60
                     val second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60
                     val timeFormat = String.format("%02d:%02d:%02d" , hour,minute,second)
                     binding.timer.text = timeFormat
                 }

                 override fun onFinish() {
                     binding.timer.text = "00:00:00"
                 }

             }
             countDownTimer.start()

         }

         }
    inner class ImageButtonViewHolder(private val binding: ItemImageButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    fun submitList(list: List<Product>){
        diffUtil.submitList(list)
        dataLoaded =true
        notifyDataSetChanged()

    }
    }
