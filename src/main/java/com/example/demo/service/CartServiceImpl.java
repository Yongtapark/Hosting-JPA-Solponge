package com.example.demo.service;


import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.repository.cart.CartItemRepository;

import com.example.demo.repository.cart.CartRepository;
import com.example.demo.service.interfaces.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;


    public CartServiceImpl(CartItemRepository cartItemRepository, CartRepository repository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = repository;
    }

    @Override
    public CartItem addItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void updateItem(Long cart_item_num, CartItem cartItem) {
        CartItem item = findItem(cart_item_num);
        item.setCartItemNum(cartItem.getCartItemNum());
        item.setMember(cartItem.getMember());
        item.setProduct(cartItem.getProduct());
        item.setCartItemStock(cartItem.getCartItemStock());
    }

    @Override
    public void deleteItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem findItem(Long cartItemNum) {
       return cartItemRepository.findById((long) cartItemNum).get();
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getMyCart(Long memberNo) {
        return cartRepository.findByMemberMemberNum(memberNo).get();
    }

    /*@Override
    public List<CartList> cartList(int member_num) {
        return cartQueryRepository.showMyCart(member_num);
    }*/

    @Override
    public void deleteCartItemByMember(Long MEMBER_NO) {
        CartItem item = findItem(MEMBER_NO);
        cartItemRepository.delete(item);
    }

    @Override
    public void deleteCartByMember(Long memberNo) {
        Cart myCart = getMyCart(memberNo);
        cartRepository.delete(myCart);
    }

   /* @Override
    public List<CartList> showMyCart(int MEMBER_NUM) {
        return cartQueryRepository.showMyCart(MEMBER_NUM);
    }*/
}