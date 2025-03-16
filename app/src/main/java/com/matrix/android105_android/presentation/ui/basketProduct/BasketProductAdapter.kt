package com.matrix.android105_android.presentation.ui.basketProduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matrix.android105_android.data.network.fireBase.Repository.home.products.Product
import com.matrix.android105_android.databinding.ItemBasketBinding

class BasketProductAdapter(
    private val increaseClick:(String,Long)->Unit,
    private val decreaseClick:(String,Long)->Unit,
    private val updateQuantity:(String, Int)->Unit
):RecyclerView.Adapter<BasketProductAdapter.BasketProductViewHolder>() {


    private val diffCallBack = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }


    }

    private val diffUtil = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketProductViewHolder {
      val binding = ItemBasketBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return BasketProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: BasketProductViewHolder, position: Int) {
     val basketProducts = diffUtil.currentList[position]
        return holder.bind(basketProducts)
    }

    inner class BasketProductViewHolder(private val binding:ItemBasketBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(basketProduct:Product){
            Glide.with(binding.root.context)
                .load(basketProduct.companyLogo)
                .into(binding.imgCompany)
            binding.txtCompany.text = basketProduct.companyName
            Glide.with(binding.root.context)
                .load(basketProduct.image)
                .into(binding.imgProduct)
            binding.txtProductName.text = basketProduct.name
            binding.discountRate.text = basketProduct.discountRate
            binding.discountedPrice.text = basketProduct.discountPrice.toString()
            binding.price.text = basketProduct.price.toString()
            binding.txtCreditMonth.text = basketProduct.credit
            binding.txtTotalPayment.text = basketProduct.discountPrice.toString()
            binding.tvNumber.text = basketProduct.quantity.toString()
            updateTotalPayment(basketProduct.discountPrice , basketProduct.quantity)


            binding.btnPlus.setOnClickListener(){
                if (basketProduct.quantity<basketProduct.stock) {
                    basketProduct.quantity++
                    updateQuantity(basketProduct.id , basketProduct.quantity)
                    updateTotalPayment(basketProduct.discountPrice , basketProduct.quantity)
                    binding.tvNumber.text = basketProduct.quantity.toString()
                    increaseClick(basketProduct.id, basketProduct.stock)
                    updateFragmentTotalPrice()
                    updateFragmentDiscountPrice()
                    updateFragmentLastPrice()
                }
            }
            binding.btnMinus.setOnClickListener(){
                if (basketProduct.quantity>1) {
                    basketProduct.quantity--
                    updateQuantity(basketProduct.id , basketProduct.quantity)
                    updateTotalPayment(basketProduct.discountPrice , basketProduct.quantity)
                    binding.tvNumber.text = basketProduct.quantity.toString()
                    decreaseClick(basketProduct.id,basketProduct.stock)
                    updateFragmentTotalPrice()
                    updateFragmentDiscountPrice()
                    updateFragmentLastPrice()
                }
            }
        }
        private fun updateTotalPayment(price: Double , quantity:Int) {
            val totalPrice = price * quantity
            binding.txtTotalPayment.text = totalPrice.toString()
        }

    }
    fun getTotalPriceForItems(): Double {
        return diffUtil.currentList.sumOf { it.price * it.quantity}
    }
    fun getDiscountPrice(): Double {
        return diffUtil.currentList.sumOf {(it.price - it.discountPrice) * it.quantity}
    }

    fun getLastPrice():Double{
        
        return diffUtil.currentList.sumOf { it.discountPrice *it.quantity }
    }


    fun submitList(list: List<Product>){
        diffUtil.submitList(list)
        updateFragmentTotalPrice()
        updateFragmentDiscountPrice()
        updateFragmentLastPrice()
    }
    var updateFragmentTotalPrice: () -> Unit = {}
    var updateFragmentDiscountPrice: () -> Unit = {}
    var updateFragmentLastPrice: () -> Unit = {}
}