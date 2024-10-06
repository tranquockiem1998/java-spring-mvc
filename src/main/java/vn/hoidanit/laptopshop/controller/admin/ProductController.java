package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

@Controller
public class ProductController {

    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @RequestMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("product1", products);
        return "admin/product/show";
    }

    @RequestMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }

    // @GetMapping("/admin/product")
    // public String getProduct() {
    // return "admin/product/show";
    // }

    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productImageFile") MultipartFile file) {
        // Validate here
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }

        if (newProductBindingResult.hasErrors())
            return "/admin/product/create";

        String avatar = this.uploadService.handleSaveUploadFile(file, "product");
        product.setAvatar(avatar);

        this.productService.handleSaveProduct(product);

        return "redirect:/admin/product";
    }

    @RequestMapping("/admin/product/update/{id}")
    public String getUpdateProduct(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("newProduct", currentProduct);
        return "admin/product/updateProduct";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productImageFile") MultipartFile file) {
        if (newProductBindingResult.hasErrors()) {
            Product currentProduct = this.productService.getProductById(product.getId());
            product.setAvatar(currentProduct.getAvatar());
            model.addAttribute("newProduct", product);
            return "/admin/product/updateProduct";
        }

        Product currentProduct = this.productService.getProductById(product.getId());
        if (currentProduct != null) {
            if (!file.isEmpty()) {
                String avatar = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setAvatar(avatar);
            }
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setQuantity(product.getQuantity());

            currentProduct.setFactory(product.getFactory());
            currentProduct.setTarget(product.getTarget());

            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newProduct", new Product());
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProductPage(Model model, @ModelAttribute("newProduct") Product product) {
        this.productService.deleteProduct(product.getId());
        return "redirect:/admin/product";
    }
}
