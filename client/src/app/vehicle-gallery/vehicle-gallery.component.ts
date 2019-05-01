import { Component, OnInit } from '@angular/core';
import {NgxGalleryAnimation, NgxGalleryImage, NgxGalleryOptions} from 'ngx-gallery';
import {ImagesService} from '../_services/images.service';
import {AlertService} from '../_services/alert.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-vehicle-gallery',
  templateUrl: './vehicle-gallery.component.html',
  styleUrls: ['./vehicle-gallery.component.scss']
})
export class VehicleGalleryComponent implements OnInit {
  galleryOptions: NgxGalleryOptions[];
  galleryImages: NgxGalleryImage[] = [];
  ngxImage: NgxGalleryImage[];
  imageURL: any;
  img: any;
  unsafeImageURL: any;
  imageToShow: any;
  isImageLoading: any;
  sub: Subscription;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private imageService: ImagesService,
    private alertService: AlertService,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit() {
    this.galleryOptions = [
      {
        width: '600px',
        height: '400px',
        thumbnailsColumns: 4,
        imageAnimation: NgxGalleryAnimation.Slide
      },
      // max-width 800
      {
        breakpoint: 800,
        width: '100%',
        height: '600px',
        imagePercent: 80,
        thumbnailsPercent: 20,
        thumbnailsMargin: 20,
        thumbnailMargin: 20
      },
      // max-width 400
      {
        breakpoint: 400,
        preview: false
      }
    ];

    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.getImageFromService(id);
        //this.galleryImages = this.imagesToShow; // TODO does this work?
      } else {
        this.alertService.error("No ID found");
      }
    });


    /*this.galleryImages = [
      {
        small: 'assets/1-car.png',
        medium: 'assets/1-car.png',
        big: 'assets/1-car.png' // TODO delete image
      },
      {
        small: 'assets/2-car.jpg',
        medium: 'assets/2-car.jpg',
        big: 'assets/2-car.jpg' // TODO delete image
      }
    ];*/
  }

  createImageFromBlob(image: Blob) {
    const reader = new FileReader();

    reader.addEventListener('load', () => {
      // this.imagesToShow.push(reader.result); // TODO check if it works at all
      this.imageToShow = reader.result;
      // this.imageToShow = image;
      console.log(image);
      console.log(this.imageToShow);
      // const imgURL = URL.createObjectURL(image);
      const img = {
        small: this.imageToShow,
        medium: this.imageToShow,
        big: this.imageToShow
      };
      /*const img = {
        small: imgURL,
        medium: imgURL,
        big: imgURL
      };*/
      this.galleryImages.push(img);
      console.log(this.imageToShow);
      console.log(this.galleryImages);
    }, false);
    if (image) {
      reader.readAsDataURL(image);
      this.unsafeImageURL = (window.URL ? URL : webkitURL).createObjectURL(image);
      // this.imageURL = this.sanitizer.bypassSecurityTrustUrl(this.unsafeImageURL);
      console.log(this.sanitizer.bypassSecurityTrustUrl(this.unsafeImageURL));
    }
    /*if (imageURL) {
      this.ngxImage = [{small: imageURL, medium: imageURL, big: imageURL}];
      this.galleryImages.push(this.ngxImage[0]);
    }*/
  }

  getImageFromService(vehicleID: string) {
    this.isImageLoading = true;
    this.imageService.getImages(vehicleID).subscribe(data => {
      console.log(data);
      const blob = new Blob([JSON.stringify(data.image)], { type: 'image/' + data.imageContentType});
      // this.createImageFromBlob(blob);
//      this.imageToShow.push(imageURL);
      console.log(data.image);
      console.log(blob);
      this.createImageFromBlob(blob); // TODO remove, ere for testing with one image and not expecting an array.
      for (let i = 0; i < data.length; i++) {
        console.log(data[i]);
        this.createImageFromBlob(data[i]);
      }
      this.isImageLoading = true;
      console.log(this.galleryImages);
    }, error => {
      this.isImageLoading = false;
      this.alertService.error(error);
      console.log(error);
    });
  }
}
