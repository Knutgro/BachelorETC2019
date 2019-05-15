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
  ngxImage: NgxGalleryImage;
  imageURL: any;
  file: File;
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
      } else {
        this.alertService.error('No ID found');
      }
    });
  }

  getImageFromService(vehicleID: string) {
    this.isImageLoading = true;
    this.imageService.getImages(vehicleID).subscribe(data => {
      const image = this.imageService.convertImage(data.image, data.imageContentType);
      this.ngxImage = image.substring(0, image.length);
      console.log(image);
      const img = {
        small: this.ngxImage,
        medium: this.ngxImage,
        big: this.ngxImage
      };
      console.log(img);
      this.galleryImages.push(img);
    }, error => {
      this.isImageLoading = false;
      this.alertService.error(error);
      console.log(error);
    });
    console.log(this.galleryImages);
  }
}
