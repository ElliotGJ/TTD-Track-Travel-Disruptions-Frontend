package com.example.tracktraveldisruptionsapp.resources;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
public class ItemSpaceDecorator extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public ItemSpaceDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = verticalSpaceHeight;
    }
}